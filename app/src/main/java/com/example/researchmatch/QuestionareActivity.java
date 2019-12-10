package com.example.researchmatch;

//Test Post from Jay

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.surveymonkey.surveymonkeyandroidsdk.SurveyMonkey;
import com.surveymonkey.surveymonkeyandroidsdk.utils.SMError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class QuestionareActivity extends AppCompatActivity {

    public static final int SM_REQUEST_CODE = 0;
    public static final String SM_RESPONDENT = "smRespondent";
    public static final String SM_ERROR = "smError";
    public static final String RESPONSES = "responses";
    public static final String QUESTION_ID = "question_id";
    public static final String FEEDBACK_QUESTION_ID = "813797519";
    public static final String ANSWERS = "answers";
    public static final String ROW_ID = "row_id";
    public static final String FEEDBACK_FIVE_STARS_ROW_ID = "9082377273";
    public static final String FEEDBACK_POSITIVE_ROW_ID_2 = "9082377274";
    public static final String SAMPLE_APP = "Sample App";
    public static final String SURVEY_HASH = "RDM75TH";
    // Initialize the SurveyMonkey SDK like so

    private SurveyMonkey s = new SurveyMonkey();

    private SurveyMonkey sdkInstance = new SurveyMonkey();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionare);

        s.onStart(this, SAMPLE_APP, SM_REQUEST_CODE, SURVEY_HASH);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void takeSurvey(View view) {
        //This is how you display a survey for the user to take
        // Remember: you must supply the parent activity (e.g. this), your own request code (to differentiate from other activities), and the collector hash of the SDK collector you've created at SurveyMonkey.com
        s.startSMFeedbackActivityForResult(this, SM_REQUEST_CODE, SURVEY_HASH);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //This is where you consume the respondent data returned by the SurveyMonkey Mobile Feedback SDK
        //In this example, we deserialize the user's response, check to see if they gave our app 4 or 5 stars, and then provide visual prompts to the user based on their response
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == RESULT_OK) {
            boolean isPromoter = false;
            try {
                String respondent = intent.getStringExtra(SM_RESPONDENT);
                Log.d("SM", respondent);
                JSONObject surveyResponse = new JSONObject(respondent);
                JSONArray responsesList = surveyResponse.getJSONArray(RESPONSES);
                JSONObject response;
                JSONArray answers;
                JSONObject currentAnswer;
                for (int i = 0; i < responsesList.length(); i++) {
                    response = responsesList.getJSONObject(i);
                    if (response.getString(QUESTION_ID).equals(FEEDBACK_QUESTION_ID)) {
                        answers = response.getJSONArray(ANSWERS);
                        for (int j = 0; j < answers.length(); j++) {
                            currentAnswer = answers.getJSONObject(j);
                            if (currentAnswer.getString(ROW_ID).equals(FEEDBACK_FIVE_STARS_ROW_ID) || currentAnswer.getString(ROW_ID).equals(FEEDBACK_POSITIVE_ROW_ID_2)) {
                                isPromoter = true;
                                break;
                            }
                        }
                        if (isPromoter) {
                            break;
                        }
                    }
                }
            } catch (JSONException e) {
                Log.getStackTraceString(e);
            }
            if (isPromoter) {
                Toast t = Toast.makeText(this, getString(R.string.promoter_prompt), Toast.LENGTH_LONG);
                t.show();
            } else {
                Toast t = Toast.makeText(this, getString(R.string.detractor_prompt), Toast.LENGTH_LONG);
                t.show();
            }
        } else {
            Toast t = Toast.makeText(this, getString(R.string.error_prompt), Toast.LENGTH_LONG);
            t.show();
            SMError e = (SMError) intent.getSerializableExtra(SM_ERROR);
            Log.d("SM-ERROR", e.getDescription());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.login) {
            Intent loginIntent = new Intent(this, LoginActivity.class);
            startActivity(loginIntent);

        }else if(item.getItemId() == R.id.register) {
            Intent searchIntent = new Intent(this, RegisterActivity.class);
            startActivity(searchIntent);
        }else if(item.getItemId() == R.id.createpost) {
            Intent postIntent = new Intent(this, PostActivity.class);
            startActivity(postIntent);

        } else if(item.getItemId() == R.id.itemProfilePage) {
            Intent searchIntent = new Intent(this, ProfileActivity.class);
            startActivity(searchIntent);

        } else if(item.getItemId() == R.id.questionnaire) {
            Intent searchIntent = new Intent(this, QuestionareActivity.class);
            startActivity(searchIntent);
        }else if(item.getItemId() == R.id.logout) {
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            Toast.makeText(this, "Signed out", Toast.LENGTH_SHORT).show();
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);

    }


}

