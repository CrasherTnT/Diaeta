package tnt_codefest.diaeta.DietPlan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tnt_codefest.diaeta.R;

public class DietPlan extends AppCompatActivity implements DietPlanDialog.TaskListener {
    private CardView day1, day2, day3, day4, day5, day6, day7;
    private TextView label_percentage;
    private CharSequence[] tasks_day1 = {" 2 Eggs"," 1 Hotdog"," 1 Cup of Rice "," 8 Glasses of Water "};

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

        day1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(1, tasks_day1);
            }
        });

    }

    public void openDialog(int day, CharSequence[] tasks){
        DietPlanDialog dietPlanDialog = new DietPlanDialog();
        dietPlanDialog.setDay(day);
        dietPlanDialog.setTasks(tasks);
        dietPlanDialog.show(getSupportFragmentManager(), "Diet Plan");
    }

    @Override
    public void completeTasks(ArrayList list_task) {
        String final_selection = "";

        for (Object task :list_task){
            final_selection = final_selection + task + "\n";
        }
        Toast.makeText(this, final_selection, Toast.LENGTH_SHORT).show();
    }
}
