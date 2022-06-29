package za.co.programme.jurrius.fragmentswithrecyclerview;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class AddPersonFrag extends Fragment {
    View rootView;
    Button btnAdd;

    public AddPersonFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(container == null){
            return null;
        }
        rootView = inflater.inflate(R.layout.fragment_add_person, container, false);
        btnAdd = (Button) rootView.findViewById(R.id.btnAdd);

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
        return rootView;
    }
//    @Override
//    public void onViewCreated(View view, Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        view = getView().findViewById(R.id.fragmentContainerAddPersonFrag);
//    }
}