package com.example.jogodamemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView textViewSequencia;
    private Button buttonClick, buttonVerificar;
    private LinearLayout linearSequencia, linearResposta;
    private EditText editText1, editText2, editText3, editText4, editText5,
            editText6, editText7, editText8, editText9, editText10;
    private Handler handler;
    private Integer numberG;
    private static final String TAG = MainActivity.class.toString();
    private List<Integer> sorteio = new ArrayList<>();
    private List<String> resposta = new ArrayList<>();
    private int pontuacao = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Rodar Sequencia
        textViewSequencia = findViewById(R.id.textViewSequencia);
        buttonClick = findViewById(R.id.buttonComecar);
        linearSequencia = findViewById(R.id.linearSequencia);

        //Verificar respostas
        linearResposta = findViewById(R.id.linearResposta);
        buttonVerificar = findViewById(R.id.buttonVerificar);

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);
        editText7 = findViewById(R.id.editText7);
        editText8 = findViewById(R.id.editText8);
        editText9 = findViewById(R.id.editText9);
        editText10 = findViewById(R.id.editText10);

    }//onCreate


    public void gerarSequencia(View view) {
        if (view.getId() == R.id.buttonComecar) {
            Log.i(TAG, String.valueOf(Thread.currentThread()));
            handler = new Handler();
            new Thread(new Runnable() {
                @Override
                public void run() {

                    Log.i(TAG, String.valueOf(Thread.currentThread()));
                    Random random = new Random();

                    for (int i = 0; i < 10; i++) {
                        numberG = random.nextInt(21);
                        sorteio.add(numberG);

                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.i(TAG, String.valueOf(Thread.currentThread()));
                                textViewSequencia.setText(String.valueOf(numberG));
                            }
                        });
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textViewSequencia.setText("Preencha");
                            linearSequencia.setVisibility(View.INVISIBLE);
                            linearResposta.setVisibility(View.VISIBLE);
                            buttonVerificar.setVisibility(View.VISIBLE);

                            System.out.println(sorteio);
                        }
                    });
                }
            }).start();
        }//if



    }//click

    public void preencherResposta(View view) {
        if(view.getId() == R.id.buttonVerificar){
            //TODO Ler os editText
            //TODO Inserir valores editText na lista respostas
            resposta.add(editText1.getText().toString());
            resposta.add(editText2.getText().toString());
            resposta.add(editText3.getText().toString());
            resposta.add(editText4.getText().toString());
            resposta.add(editText5.getText().toString());
            resposta.add(editText6.getText().toString());
            resposta.add(editText7.getText().toString());
            resposta.add(editText8.getText().toString());
            resposta.add(editText9.getText().toString());
            resposta.add(editText10.getText().toString());
        }//if
    }//lerEditeText

    public void verificar(View view) {
        //TODO Verificar se as respostas são igual
        preencherResposta(view);

        for (int i=0; i<10; i++){
            if(sorteio.get(i).toString().equals(resposta.get(i))){
                pontuacao= pontuacao+1;
            }
        }
        resposta.clear();
        //TODO Atualizar textViewSequencia com quantidade acertadas
        linearResposta.setVisibility(View.INVISIBLE);
        buttonVerificar.setVisibility(View.INVISIBLE);
        textViewSequencia.setVisibility(View.VISIBLE);
        textViewSequencia.setText("Você fez "+pontuacao+" acertos.");
    }
}//class
