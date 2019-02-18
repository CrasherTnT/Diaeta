package tnt_codefest.diaeta.DietPlan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

import tnt_codefest.diaeta.R;

public class DietPlanDialog extends AppCompatDialogFragment {
    private CheckBox item1, item2, item3;
    private TaskListener listener;
    ArrayList<String> list_tasks = new ArrayList<>();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.dialog_dietplan, null);

        item1 = view.findViewById(R.id.checkbox_task1);
        item2 = view.findViewById(R.id.checkbox_task2);
        item3 = view.findViewById(R.id.checkbox_task3);

        builder.setView(view)
               .setTitle("DAY #")
               .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ArrayList<String> task = taskClicked(view);
                        listener.completeTasks(task);
                    }
                })
               .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try{
            listener = (TaskListener) context;
        }
        catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement TaskListener");
        }
    }

    public interface TaskListener{
        void completeTasks(ArrayList<String> list_task);
    }

    public ArrayList<String> taskClicked(View view){
        CheckBox task = (CheckBox) view;
        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()){
            case R.id.checkbox_task1:
                if (checked){
                    Log.d("DIET TASKS", task.getText().toString() + "CHECKED");
                    list_tasks.add(task.getText().toString());
                }

                else{
                    Log.d("DIET TASKS", task.getText().toString() + "UNCHECKED");
                    list_tasks.remove(task.getText().toString());
                }
                break;

            case R.id.checkbox_task2:
                if (checked){
                    Log.d("DIET TASKS", task.getText().toString() + "CHECKED");
                    list_tasks.add(task.getText().toString());
                }

                else{
                    Log.d("DIET TASKS", task.getText().toString() + "UNCHECKED");
                    list_tasks.remove(task.getText().toString());
                }
                break;

            case R.id.checkbox_task3:
                if (checked){
                    Log.d("DIET TASKS", task.getText().toString() + "CHECKED");
                    list_tasks.add(task.getText().toString());
                }

                else{
                    Log.d("DIET TASKS", task.getText().toString() + "UNCHECKED");
                    list_tasks.remove(task.getText().toString());
                }
                break;

            default:
                break;
        }

        return list_tasks;
    }
}
