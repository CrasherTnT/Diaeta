package tnt_codefest.diaeta.DietPlan;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import java.util.ArrayList;


public class DietPlanDialog extends AppCompatDialogFragment {
    private TaskListener listener;
    final ArrayList selectedItems =new ArrayList();

    // To set the day in the dialog

    private int day;
    private CharSequence[] tasks;


    public void setDay(int day){
        this.day = day;
    }
    public void setTasks(CharSequence[] tasks){
        this.tasks = tasks;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("DAY " + day)
                .setMultiChoiceItems(tasks, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int index, boolean isChecked) {
                        switch (index){
                            case 0:
                                if (isChecked){
                                    selectedItems.add(tasks[index]);
                                }
                                else{
                                    selectedItems.remove(tasks[index]);
                                }
                        }
                        }

                })
                .setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.completeTasks(selectedItems);
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
        void completeTasks(ArrayList list_task);
    }
}