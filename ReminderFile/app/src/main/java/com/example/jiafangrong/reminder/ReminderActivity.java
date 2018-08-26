package com.example.jiafangrong.reminder;

//import android.support.v7.app.ActionBar;
//import android.app.AlertDialog;
import android.app.Dialog;
import android.database.Cursor;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.app.ActionBar;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;


public class ReminderActivity extends AppCompatActivity {

    private ListView mListView;
    private RemindersDbAdapter mDbAdapter;
    private ReminderSimpleCursorAdapter mCursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        mListView = (ListView) findViewById(R.id.reminders_list_view);

        mListView.setDivider(null);
        mDbAdapter = new RemindersDbAdapter(this);
        mDbAdapter.open();

        if (savedInstanceState == null){
            mDbAdapter.deleteAllReminders();
            insetSomeReminders();
        }

        Cursor cursor = mDbAdapter.fetchAllReminders();
        //from colums defined in the db
        String[] form = new String[]{
                RemindersDbAdapter.COL_CONTENT
        };

        //to the ids of views in the layout
        int[] to = new int[]{
                R.id.row_text
        };

        mCursorAdapter = new ReminderSimpleCursorAdapter(
                //context
                ReminderActivity.this,
                //the layout of the row
                R.layout.reminder_row,
                //cursor
                cursor,
                //from colums defined in the db
                form,
                //to the ids of views in the layout
                to,
                //flag - not used
                0
        );

        mListView.setAdapter(mCursorAdapter);

//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                this,
//        R.layout.reminder_row,
//        R.id.row_text,
//        new  String[]{"first","second","third"}
//                );
//        mListView.setAdapter(arrayAdapter);
//
//        ActionBar actionBar = getActionBar();
//        System.out.println(0);
//        if (actionBar!=null){
//
//            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//            actionBar.setCustomView(R.layout.actionbar);
//        }
        // when we click an individual item in the listview
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int masterPostion, long l) {
//                Toast.makeText(ReminderActivity.this, "clicked " + i, Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(ReminderActivity.this);
                ListView modeListView = new ListView(ReminderActivity.this);
                String[] modes = new  String[]{"edit","delete"};

                ArrayAdapter<String> modeAdapter = new ArrayAdapter<>(ReminderActivity.this,  android.R.layout.simple_list_item_1, android.R.id.text1, modes);
                modeListView.setAdapter(modeAdapter);
                builder.setView(modeListView);

                final Dialog dialog = builder.create();
                dialog.show();

                modeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //edit reminder
                        if (i==0){
//                            Toast.makeText(ReminderActivity.this, "edit " + masterPostion, Toast.LENGTH_SHORT).show();
                            int nId = getIdFromPosition(masterPostion);
                            Reminder reminder = mDbAdapter.fetchReminderById(nId);
                            fireCustomDialog(reminder);

                        }else {
//                            Toast.makeText(ReminderActivity.this, "delete " + masterPostion, Toast.LENGTH_SHORT).show();
                            mDbAdapter.deleteReminderById(getIdFromPosition(masterPostion));
                            mCursorAdapter.changeCursor(mDbAdapter.fetchAllReminders());
                        }

                        dialog.dismiss();
                    }
                });


            }
        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
            mListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
                @Override
                public void onItemCheckedStateChanged(ActionMode actionMode, int i, long l, boolean b) {

                }

                @Override
                public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
                    MenuInflater inflater = actionMode.getMenuInflater();
                    inflater.inflate(R.menu.cam_menu, menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {

                    switch (menuItem.getItemId()){
                        case R.id.menu_item_delete_reminder:
                            for (int nC = mCursorAdapter.getCount() - 1; nC >= 0; nC--){
                                if (mListView.isItemChecked(nC)){
                                    mDbAdapter.deleteReminderById(getIdFromPosition(nC));
                                }
                            }
                            actionMode.finish();
                            mCursorAdapter.changeCursor(mDbAdapter.fetchAllReminders());
                            return true;
                    }
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode actionMode) {

                }
            });
        }
    }

    private int getIdFromPosition(int nC) {
        return (int) mCursorAdapter.getItemId(nC);
    }

    private void insetSomeReminders() {
        mDbAdapter.createReminder("buy1",true);
        mDbAdapter.createReminder("send2",false);
        mDbAdapter.createReminder("buy3",true);
        mDbAdapter.createReminder("send4",false);
        mDbAdapter.createReminder("buy5",true);
        mDbAdapter.createReminder("send6",false);
        mDbAdapter.createReminder("buy7",true);
        mDbAdapter.createReminder("send8",false);
        mDbAdapter.createReminder("buy9",true);
        mDbAdapter.createReminder("send11",false);
        mDbAdapter.createReminder("buy12",true);
        mDbAdapter.createReminder("send13",false);
        mDbAdapter.createReminder("buy14",true);
        mDbAdapter.createReminder("send15",false);
        mDbAdapter.createReminder("buy16",true);
        mDbAdapter.createReminder("send117",false);
        mDbAdapter.createReminder("buy18",true);
        mDbAdapter.createReminder("send19",false);
        mDbAdapter.createReminder("buy21",true);
        mDbAdapter.createReminder("send22",false);
        mDbAdapter.createReminder("buy23",true);
        mDbAdapter.createReminder("send24",false);
        mDbAdapter.createReminder("buy25",true);
        mDbAdapter.createReminder("send26",false);
        mDbAdapter.createReminder("buy27",true);
        mDbAdapter.createReminder("send28",false);
    }

    private void fireCustomDialog(final Reminder reminder){

        final  Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_custom);

        TextView titleView= (TextView) dialog.findViewById(R.id.custom_title);
        final EditText editCustom = (EditText) dialog.findViewById(R.id.custom_edit_reminder);
        Button commitButton = (Button) dialog.findViewById(R.id.custom_button_commit);
        final CheckBox checkBox = (CheckBox) dialog.findViewById(R.id.custom_check_box);
        LinearLayout rootLayout = (LinearLayout) dialog.findViewById(R.id.custom_root_layout);
        final boolean isEditOperation = (reminder != null);

        // this is for an edit
        if (isEditOperation){
            titleView.setText("Edit Reminder");
            checkBox.setChecked(reminder.getImportant()==1);
            editCustom.setText(reminder.getContent());
            rootLayout.setBackgroundColor(getResources().getColor(R.color.blue));

        }

        commitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String reminderText = editCustom.getText().toString();

                if (isEditOperation){
                    Reminder reminderEdited = new Reminder(reminder.getId(),reminderText,checkBox.isChecked()?1:0);
                    mDbAdapter.updateReminder(reminderEdited);
                }else {
                    mDbAdapter.createReminder(reminderText,checkBox.isChecked());

                }

                mCursorAdapter.changeCursor(mDbAdapter.fetchAllReminders());
                dialog.dismiss();
            }
        });

        Button buttonCancel = (Button) dialog.findViewById(R.id.custom_button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

//    public boolean onOptionsItemSelectd(MenuItem item){
//        switch (item.getItemId()){
//            case R.id.action_new:
//                //create new Reminder
//                fireCustomDialog(null);
//                return true;
//            case R.id.action_exit:
//                finish();
//                return true;
//                default:
//                    return false;
//        }
//    }

}

