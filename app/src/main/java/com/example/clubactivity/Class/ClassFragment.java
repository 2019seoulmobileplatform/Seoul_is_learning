package com.example.clubactivity.Class;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.clubactivity.R;


public class ClassFragment extends Fragment implements View.OnClickListener {
    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.class_, container, false);

       /* ImageButton gwangjin_button = (ImageButton)view.findViewById(R.id.imageView3);

        gwangjin_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClassList.class);
                startActivity(intent);
            }
        });


        FrameLayout eunpyung_button = view.findViewById(R.id.fl_mainfragment_eunpyung);

        eunpyung_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ClassList.class);
                startActivity(intent);
            }
        });*/

        FrameLayout eunpyung_button = view.findViewById(R.id.fl_mainfragment_eunpyung);
        FrameLayout gangbuk_button = view.findViewById(R.id.fl_mainfragment_gangbuk);
        FrameLayout dobong_button = view.findViewById(R.id.fl_mainfragment_dobong);
        FrameLayout nowon_button = view.findViewById(R.id.fl_mainfragment_nowon);
        FrameLayout seodaemun_button = view.findViewById(R.id.fl_mainfragment_seodaemun);
        FrameLayout jongro_button = view.findViewById(R.id.fl_mainfragment_jongro);
        FrameLayout seongbuk_button = view.findViewById(R.id.fl_mainfragment_seongbuk);
        FrameLayout mapo_button = view.findViewById(R.id.fl_mainfragment_mapo);
        FrameLayout junggu_button = view.findViewById(R.id.fl_mainfragment_junggu);
        FrameLayout dongdaemun_button = view.findViewById(R.id.fl_mainfragment_dongdaemun);
        FrameLayout yongsan_button = view.findViewById(R.id.fl_mainfragment_yongsan);
        FrameLayout seongdong_button = view.findViewById(R.id.fl_mainfragment_seongdong);
        FrameLayout jungrang_button = view.findViewById(R.id.fl_mainfragment_jungnang);
        FrameLayout gwangjin_button = view.findViewById(R.id.fl_mainfragment_gwangjin);

        FrameLayout gangseo_button = view.findViewById(R.id.fl_mainfragment_gangseo);
        FrameLayout yangcheon_button = view.findViewById(R.id.fl_mainfragment_yangcheon);
        FrameLayout guro_button = view.findViewById(R.id.fl_mainfragment_guro);
        FrameLayout yeongdeungpo_button = view.findViewById(R.id.fl_mainfragment_yeongdeungpo);
        FrameLayout dongjak_button = view.findViewById(R.id.fl_mainfragment_dongjak);
        FrameLayout gwanak_button = view.findViewById(R.id.fl_mainfragment_gwanak);
        FrameLayout geumcheon_button = view.findViewById(R.id.fl_mainfragment_geumcheon);
        FrameLayout seocho_button = view.findViewById(R.id.fl_mainfragment_seocho);
        FrameLayout gangnam_button = view.findViewById(R.id.fl_mainfragment_gangnam);
        FrameLayout gangdong_button = view.findViewById(R.id.fl_mainfragment_gangdong);
        FrameLayout songpa_button = view.findViewById(R.id.fl_mainfragment_songpa);


        eunpyung_button.setOnClickListener(this);
        gangbuk_button.setOnClickListener(this);
        dobong_button.setOnClickListener(this);
        nowon_button.setOnClickListener(this);
        seodaemun_button.setOnClickListener(this);
        jongro_button.setOnClickListener(this);
        seongbuk_button.setOnClickListener(this);
        mapo_button.setOnClickListener(this);
        junggu_button.setOnClickListener(this);
        dongdaemun_button.setOnClickListener(this);
        yongsan_button.setOnClickListener(this);
        seongdong_button.setOnClickListener(this);
        jungrang_button.setOnClickListener(this);
        gwangjin_button.setOnClickListener(this);

        gangseo_button.setOnClickListener(this);
        yangcheon_button.setOnClickListener(this);
        guro_button.setOnClickListener(this);
        yeongdeungpo_button.setOnClickListener(this);
        dongjak_button.setOnClickListener(this);
        gwanak_button.setOnClickListener(this);
        geumcheon_button.setOnClickListener(this);
        seocho_button.setOnClickListener(this);
        gangnam_button.setOnClickListener(this);
        gangdong_button.setOnClickListener(this);
        songpa_button.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View v){
        int id = v.getId();

        switch(id){
            case R.id.fl_mainfragment_eunpyung :
            case R.id.fl_mainfragment_gangbuk :
            case R.id.fl_mainfragment_dobong :
            case R.id.fl_mainfragment_nowon :
            case R.id.fl_mainfragment_seodaemun :
            case R.id.fl_mainfragment_jongro :
            case R.id.fl_mainfragment_seongbuk :
            case R.id.fl_mainfragment_mapo :
            case R.id.fl_mainfragment_junggu :
            case R.id.fl_mainfragment_dongdaemun :
            case R.id.fl_mainfragment_yongsan :
            case R.id.fl_mainfragment_seongdong :
            case R.id.fl_mainfragment_jungnang :
            case R.id.fl_mainfragment_gwangjin :

            case R.id.fl_mainfragment_gangseo  :
            case R.id.fl_mainfragment_yangcheon  :
            case R.id.fl_mainfragment_guro  :
            case R.id.fl_mainfragment_yeongdeungpo  :
            case R.id.fl_mainfragment_dongjak  :
            case R.id.fl_mainfragment_gwanak  :
            case R.id.fl_mainfragment_geumcheon  :
            case R.id.fl_mainfragment_seocho  :
            case R.id.fl_mainfragment_gangnam  :
            case R.id.fl_mainfragment_gangdong  :
            case R.id.fl_mainfragment_songpa  :


                Intent intent = new Intent(getActivity(), ClassList.class);
                intent.putExtra("param", v.getId()); //지역구 명을 뿌려준다. 수정중중
               startActivity(intent);
                break;
        }
    }



}