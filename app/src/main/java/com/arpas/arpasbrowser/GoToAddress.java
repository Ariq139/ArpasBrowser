package com.arpas.arpasbrowser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GoToAddress extends Activity {
    public static final String EXTRA_REPLY	= "com.arpas.arpasbrowser.extra.REPLY";
    private EditText mReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.address);

        mReply = findViewById(R.id.editText_second);

    }

    public	void	returnReply(View view)	{
        String	reply	=	mReply.getText().toString();
        if(reply.startsWith("http://")==false){
            reply = "http://" + reply;
        }
        else if(reply.startsWith("http://www.")==false){
            reply = "http://www." + reply;
        }
        Intent	replyIntent	=	new	Intent();
        replyIntent.putExtra(EXTRA_REPLY,	reply);
        setResult(RESULT_OK,	replyIntent);

        finish();
    }

}

