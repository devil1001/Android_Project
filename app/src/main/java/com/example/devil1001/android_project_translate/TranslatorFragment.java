package com.example.devil1001.android_project_translate;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.example.devil1001.android_project_translate.Data.WordsContract;
import com.example.devil1001.android_project_translate.Data.WordsDbHelper;
import com.example.devil1001.android_project_translate.Helpers.LanguageConverter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by devil1001 on 17.04.17.
 */

public class TranslatorFragment extends Fragment {

    private static final String API_KEY = "trnsl.1.1.20170417T104247Z.284db538237dc102.50e35d6b1426476770327055a87ccab04f1d66cb";

    Button btnTranslate;
    EditText txtEdit;
    TextView txtTranslated;
    SearchableSpinner leftSpinner;
    SearchableSpinner rightSpinner;
    WordsDbHelper wordsDbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.translator_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtTranslated = (TextView) getActivity().findViewById(R.id.txt_translated);
        btnTranslate = (Button) getActivity().findViewById(R.id.btn_translate);
        txtEdit = (EditText) getActivity().findViewById(R.id.text_to_translate);
        leftSpinner = (SearchableSpinner) getActivity().findViewById(R.id.left_spinner);
        rightSpinner = (SearchableSpinner) getActivity().findViewById(R.id.right_spinner);
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                translate(txtEdit.getText().toString());
            }
        });
        wordsDbHelper = WordsDbHelper.getInstance(getActivity());
    }

    private void translate(final String word) {
        ApiService api = RetroClient.getApiService();
        final String leftSpinnerDir = LanguageConverter.getInstance().convert(getActivity(), leftSpinner.getSelectedItem().toString());
        final String rightSpinnerDir = LanguageConverter.getInstance().convert(getActivity(), rightSpinner.getSelectedItem().toString());
        Call<Answer> call = api.getMyJSON(API_KEY, word, leftSpinnerDir+"-"+rightSpinnerDir);
        call.enqueue(new Callback<Answer>() {
            @Override
            public void onResponse(Call<Answer> call, Response<Answer> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(getActivity(), "GREAT", Toast.LENGTH_SHORT).show();
                    txtTranslated.setText(response.body().getText()[0]);
                    insertWord(word, response.body().getText()[0], leftSpinnerDir+'-'+rightSpinnerDir);
                } else {
                    Toast.makeText(getActivity(), "NOT GREAT", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Answer> call, Throwable t) {
                Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void insertWord(String word, String translation, String dir) {
        SQLiteDatabase db = wordsDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(WordsContract.WordEntry.COLUMN_WORD, word);
        values.put(WordsContract.WordEntry.COLUMN_TRANSLATED, translation);
        values.put(WordsContract.WordEntry.COLUMN_DIRECTION, dir);
        values.put(WordsContract.WordEntry.COLUMN_FAVOURITE, 0);
        long newRowId = db.insert(WordsContract.WordEntry.TABLE_NAME, null, values);
        //updateView();
    }

    interface NewData {
        void updateView();
    }
}