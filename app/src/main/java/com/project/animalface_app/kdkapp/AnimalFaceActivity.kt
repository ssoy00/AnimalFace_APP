package com.project.animalface_app.kdkapp

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.project.animalface_app.R
import com.project.animalface_app.databinding.ActivityAnimalFaceBinding
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date

class AnimalFaceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAnimalFaceBinding
    private lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAnimalFaceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 권한 확인 및 요청
        checkAndRequestPermissions()

        val requestGalleryLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            try {
                val calRatio = calculateInSampleSize(
                    it.data!!.data!!,
                    resources.getDimensionPixelSize(R.dimen.profile_img_width),
                    resources.getDimensionPixelSize(R.dimen.profile_img_height)
                )
                val options = BitmapFactory.Options()
                options.inSampleSize = calRatio

                var inputStream = contentResolver.openInputStream(it.data!!.data!!)
                val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                inputStream!!.close()

                binding.resultUserImage.setImageBitmap(bitmap)

                Log.d("AnimalFaceActivity", "갤러리에서 선택된 사진의 크기 비율 calRatio: $calRatio")
            } catch (e: Exception) {
                e.printStackTrace()
                Log.d("AnimalFaceActivity", "사진 출력 실패")
            }
        }

        binding.galleryBtn.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            intent.type = "image/*"
            requestGalleryLauncher.launch(intent)
        }

        val requestCameraFileLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            val calRatio = calculateInSampleSize(
                Uri.fromFile(File(filePath)),
                resources.getDimensionPixelSize(R.dimen.profile_img_width),
                resources.getDimensionPixelSize(R.dimen.profile_img_height)
            )
            val options = BitmapFactory.Options()
            options.inSampleSize = calRatio
            val bitmap = BitmapFactory.decodeFile(filePath, options)
            binding.resultUserImage.setImageBitmap(bitmap)
        }

        binding.cameraBtn.setOnClickListener {
            try {
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val storageDir: File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                val file = File.createTempFile("JPEG_${timeStamp}_", ".jpg", storageDir)

                filePath = file.absolutePath
                Log.d("AnimalFaceActivity", "filePath: $filePath")

                val photoURI: Uri = FileProvider.getUriForFile(
                    this,
                    "com.example.myapp.fileprovider",  // 패키지 이름을 직접 사용
                    file
                )
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                requestCameraFileLauncher.launch(intent)
            } catch (e: Exception) {
                Log.e("AnimalFaceActivity", "Error launching camera", e)
            }
        }
    }

    // 권한 확인 및 요청 함수
    private fun checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA)
            != android.content.pm.PackageManager.PERMISSION_GRANTED ||
            ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != android.content.pm.PackageManager.PERMISSION_GRANTED) {

            // 권한이 허용되지 않은 경우 권한 요청
            androidx.core.app.ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                100
            )
        }
    }

    // 크기를 조절해주는 함수
    private fun calculateInSampleSize(fileUri: Uri, reqWidth: Int, reqHeight: Int): Int {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true

        try {
            var inputStream = contentResolver.openInputStream(fileUri)
            BitmapFactory.decodeStream(inputStream, null, options)
            inputStream!!.close()
            inputStream = null
        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("AnimalFaceActivity", "사진 크기 비율 계산 실패")
        }

        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        var inSampleSize = 1

        if (height > reqHeight || width > reqWidth) {
            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }
}
