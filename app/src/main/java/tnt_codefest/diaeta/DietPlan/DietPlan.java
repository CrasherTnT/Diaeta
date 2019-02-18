package tnt_codefest.diaeta.DietPlan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tnt_codefest.diaeta.R;

public class DietPlan extends AppCompatActivity implements DietPlanDialog.TaskListener {
    private CardView day1, day2, day3, day4, day5, day6, day7;
    private CharSequence[] tasks_day1 = {" 2 Eggs"," 1 Hotdog"," 1 Cup of Rice "," 8 Glasses of Water "};
    private CharSequence[] tasks_day2 = {" 2 Longsilog"," 1 Carrot"," 1 Soup "," 8 Cokes"};
    private CharSequence[] tasks_day3 = {" 2 Cup of Rice"," 1 Longganisa"," 1 Raw Pork "," 8 Glasses of Wine "};
    private CharSequence[] tasks_day4 = {" 2 Nuggets"," 1 Rice"," 1 Glass of Water "," 1 Petchay  "};
    private CharSequence[] tasks_day5 = {" 2 Apple"," 1 Orange"," 1 Watermelon "," 8 Glasses of Water "};
    private CharSequence[] tasks_day6 = {" 2 Meat"," 1 Chicken"," 1 Cup of RIce "," 10 Sip of Water "};
    private CharSequence[] tasks_day7 = {" 2 Chicken Girls"," 1 Cup"," 2 Pork Boys  "," 8 Glasses of Water "};

    private ProgressBar progressBar;


    // TODO: HELP ME

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
        day2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(2, tasks_day2);
            }
        });
        day3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(3, tasks_day3);
            }
        });
        day4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(4, tasks_day4);
            }
        });
        day5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(5, tasks_day5);
            }
        });
        day6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(6, tasks_day6);
            }
        });
        day7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(7, tasks_day7);
            }
        });

        progressBar = findViewById(R.id.progress_bar);


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
