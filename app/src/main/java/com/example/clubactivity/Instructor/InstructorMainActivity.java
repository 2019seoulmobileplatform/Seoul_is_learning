package com.example.clubactivity.Instructor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.clubactivity.Class.ClassDetailActivity;
import com.example.clubactivity.Club.AddClubActivity;
import com.example.clubactivity.Club.ChatViewAdapter;
import com.example.clubactivity.Club.ChatViewItem;
import com.example.clubactivity.Constants;
import com.example.clubactivity.MyPage.EditMyInfoActivity;
import com.example.clubactivity.Network.ImageConverter;
import com.example.clubactivity.Network.NetworkTask;
import com.example.clubactivity.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;

import de.hdodenhof.circleimageview.CircleImageView;

public class InstructorMainActivity extends AppCompatActivity {

    SwipeMenuListView instructorClassList;
    ChatViewAdapter instructorClassAdapter;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    CircleImageView user_image;
    TextView user_nickname;
    TextView user_residence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructor_main);
        instructorClassList = findViewById(R.id.instructor_class_listview);
        instructorClassAdapter = new ChatViewAdapter() ;
        preferences = getSharedPreferences("preferences", MODE_PRIVATE);
        editor = preferences.edit();

        String url = "http://106.10.35.170/ImportInstructorClassList.php";
        String data = "email=" + preferences.getString("email","");
        NetworkTask networkTask = new NetworkTask(InstructorMainActivity.this, url, data, Constants.SERVER_CLASS_LIST_GET_INSTRUCTOR, instructorClassAdapter);
        networkTask.execute();
        instructorClassList.setAdapter(instructorClassAdapter);
        SetListViewCreator(instructorClassList);

        //강사 정보 미리보기 세팅
        user_image = (CircleImageView)findViewById(R.id.user_image);
        user_nickname = findViewById(R.id.user_nickname);
        user_residence = findViewById(R.id.user_residence);
        if(ImageConverter.getImageToBitmap(preferences.getString("profileImage", "")) != null)
            user_image.setImageBitmap(getImageToBitmap(preferences.getString("profileImage", "")));
        else{
            user_image.setImageResource(R.drawable.ic_account_circle_white_60dp);
        }
        user_nickname.setText(preferences.getString("nickname", ""));
        user_residence.setText(preferences.getString("residence",""));


        //클래스 클릭해서 들어가기
        instructorClassList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(InstructorMainActivity.this, ClassDetailActivity.class);
                //Intent intent = new Intent(context, TabTest.class);

                intent.putExtra("param", ((ChatViewItem)instructorClassAdapter.getItem(i)).getTitle());
                intent.putExtra("desc", ((ChatViewItem)instructorClassAdapter.getItem(i)).getDesc());

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                Bitmap bitmap = ((BitmapDrawable)((ChatViewItem)instructorClassAdapter.getItem(i)).getIcon()).getBitmap();

                Bitmap dstBitmap = Bitmap.createScaledBitmap(bitmap, Constants.IMAGE_SIZE, bitmap.getHeight()/(bitmap.getWidth()/Constants.IMAGE_SIZE), true);

                dstBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();


                intent.putExtra("area",((ChatViewItem)instructorClassAdapter.getItem(i)).getArea()); //클래스 지역구
                intent.putExtra("star",((ChatViewItem)instructorClassAdapter.getItem(i)).getStar()); //평점 뿌리기
                intent.putExtra("image",bytes);

                intent.putExtra("people", ((ChatViewItem)instructorClassAdapter.getItem(i)).getPeople());
                intent.putExtra("location", ((ChatViewItem)instructorClassAdapter.getItem(i)).getLocation());
                intent.putExtra("date", ((ChatViewItem)instructorClassAdapter.getItem(i)).getDate());
                intent.putExtra("number", ((ChatViewItem)instructorClassAdapter.getItem(i)).getPeopleNumber());
                intent.putExtra("price", ((ChatViewItem)instructorClassAdapter.getItem(i)).getPrice());
                intent.putExtra("favorite", ((ChatViewItem)instructorClassAdapter.getItem(i)).getFavorite());
                intent.putExtra("class_index", ((ChatViewItem)instructorClassAdapter.getItem(i)).getClass_index());
                intent.putExtra("number_now",((ChatViewItem)instructorClassAdapter.getItem(i)).getPeopleNumberNow()); //현재 인원수
                intent.putExtra("is_instructor", true);
                InstructorMainActivity.this.startActivity(intent);

                //startActivityForResult(intent, Constants.REQUEST_CLUB_INTRO_ENTER);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_class_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InstructorMainActivity.this, AddClassActivity.class);
                startActivityForResult(intent, Constants.REQUSET_ADD_CLASS); // 요청한 곳을 구분하기 위한 숫자, 의미없음
            }
        });

    }


    public void EditInfo(View view) {
        Intent intent = new Intent(InstructorMainActivity.this, EditMyInfoActivity.class);
        intent.putExtra("isInstructor", true);
        startActivityForResult(intent, Constants.REQUEST_EDIT_INFO_INS);
    }

    public void SetListViewCreator(SwipeMenuListView listView){
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(200);
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete_white_24dp);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        listView.setMenuCreator(creator);

        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        //예약자가 없을때/ 날짜가 지난 클래스일 때만 지우기 가능하게 // 어뎁터 새로만들어서 연결
                        ChatViewItem wholeClubItem = ((ChatViewItem)instructorClassAdapter.getItem(position));
                        instructorClassAdapter.removeItem(position);

                        instructorClassAdapter.notifyDataSetChanged();
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });
    }

    public Bitmap getImageToBitmap(String encodedImage){
        byte[] decodedByte = Base64.decode(encodedImage, Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == Constants.REQUEST_EDIT_INFO_INS){
            if(ImageConverter.getImageToBitmap(preferences.getString("profileImage", "")) != null)
                user_image.setImageBitmap(getImageToBitmap(preferences.getString("profileImage", "")));
            else{
                user_image.setImageResource(R.drawable.ic_account_circle_white_60dp);
            }
            user_nickname.setText(preferences.getString("nickname", ""));
            user_residence.setText(preferences.getString("residence",""));
        }

    }
}
