package tnt_codefest.diaeta.DietPlan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import tnt_codefest.diaeta.R;

public class DietPlan extends AppCompatActivity implements DietPlanDialog.TaskListener {
    private CardView day1, day2, day3, day4, day5, day6, day7;
    private TextView label_percentage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_plan);

        day1 = findViewById(R.id.cardview_day1);
        day2 = findViewById(R.id.cardview_day2);
        day3 = findViewById(R.id.cardview_day3);
        day4 = findViewById(R.id.cardview_day4);
        day5 = findViewById(R.id.cardview_day5);
        day6 = findViewById(R.id.cardview_day6);
        day7 = findViewById(R.id.cardview_day7);
        label_percentage = findViewById(R.id.label_percentage);

        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

    }

    public void openDialog(){
        DietPlanDialog dietPlanDialog = new DietPlanDialog();
        dietPlanDialog.show(getSupportFragmentManager(), "Diet Plan");
    }

    @Override
    public void completeTasks(ArrayList<String> list_task) {
        String final_selection = "";

        for (String task :list_task){
            final_selection = final_selection + task + "\n";
        }
        label_percentage.setText(final_selection);
    }
}
