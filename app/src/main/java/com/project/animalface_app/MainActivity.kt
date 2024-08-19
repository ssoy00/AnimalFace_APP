import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.project.animalface_app.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val menuIcon: CheckBox = findViewById(R.id.menuicon)
        val sidebar: View = findViewById(R.id.sidebar)
        val menuIconLine1: View = findViewById(R.id.menuIconLine1)
        val menuIconLine2: View = findViewById(R.id.menuIconLine2)
        val menuIconLine3: View = findViewById(R.id.menuIconLine3)

        menuIcon.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                sidebar.animate().translationX(200f).duration = 350
                ViewCompat.animate(menuIconLine1).rotation(45f).setDuration(350).start()
                ViewCompat.animate(menuIconLine2).alpha(0f).setDuration(350).start()
                ViewCompat.animate(menuIconLine3).rotation(-45f).setDuration(350).start()
            } else {
                sidebar.animate().translationX(0f).duration = 350
                ViewCompat.animate(menuIconLine1).rotation(0f).setDuration(350).start()
                ViewCompat.animate(menuIconLine2).alpha(1f).setDuration(350).start()
                ViewCompat.animate(menuIconLine3).rotation(0f).setDuration(350).start()
            }
        }

        val logo: ImageView = findViewById(R.id.logo)
        logo.setOnClickListener {
            // 로고 클릭 시 동작 정의
        }

        val myPage: ImageView = findViewById(R.id.mypage)
        myPage.setOnClickListener {
            // 마이페이지 클릭 시 동작 정의
        }
    }
}
