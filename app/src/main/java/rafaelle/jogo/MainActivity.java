package rafaelle.jogo;



import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private float topo;
    private ImageView obj;
    private Handler tempo;
    private int pontos = 0;
    private int vidas = 3;
    private TextView pts;
    private TextView vds;
    private ImageView over;

    private ImageView coracao1;
    private ImageView coracao2;
    private ImageView coracao3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //move objeto
        obj = findViewById(R.id.objeto);
        pts = findViewById(R.id.pontos);
        vds = findViewById(R.id.vidas);
        over = findViewById(R.id.gameover);
        coracao1 = findViewById(R.id.heart3);
        coracao2 = findViewById(R.id.heart2);
        coracao3 = findViewById(R.id.heart1);

        //pega as dimensões da tela
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenHeight = displayMetrics.heightPixels;
        int screenWidth = displayMetrics.widthPixels - obj.getLayoutParams().width;
      //  Log.i("medida", "largura da tela"+screenWidth);
       // Log.i("medida", "largura da objeto"+screenWidth);




        Handler tempo = new Handler();
        tempo.postDelayed(new Runnable() {
            @Override
            public void run() {
                topo += 50;
                obj.setTranslationY(topo);

                if(topo >= screenHeight){
                    topo = - obj.getLayoutParams().height;
                    obj.setTranslationY(topo);
                    int lado = (int)(Math.random() * screenWidth);
                    obj.setTranslationX(lado);

                    vidas--;
                    vds.setText("Vidas: "+vidas);
                }
                if(vidas == 2){
                    coracao3.setVisibility(View.GONE);
                }
                if(vidas == 1){
                    coracao2.setVisibility(View.GONE);
                }
                if(vidas == 0){
                    coracao1.setVisibility(View.GONE);
                }
                if (vidas > 0) {
                    tempo.postDelayed(this,50);
                }else {
                    over.setVisibility(View.VISIBLE);
                    Toast alerta = Toast.makeText(getApplicationContext(), "kkkkkkkkkkkkkkkk", Toast.LENGTH_LONG);
                     alerta.show();
                }
            }
        }, 1000);

        obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                topo = - obj.getLayoutParams().height;
                obj.setTranslationY(topo);
                int lado = (int)(Math.random() * screenWidth);
                obj.setTranslationX(lado);
               pontos++;
               pts.setText("Pontos: "+pontos);



            }

        });

    }
}