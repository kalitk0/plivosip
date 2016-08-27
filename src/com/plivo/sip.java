package com.plivo;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.media.AudioManager;
import android.util.Log;
import android.view.View;

import com.plivo.endpoint.Endpoint;
import com.plivo.endpoint.EventListener;
import com.plivo.endpoint.Incoming;
import com.plivo.endpoint.Outgoing;

public class sip extends CordovaPlugin implements EventListener {
	
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		
         
   
        
        super.initialize(cordova, webView); 
    }
	private CallbackContext mInitCallbackContext;
	public final static String EXTRA_MESSAGE = "com.plivo.example.MESSAGE";
    // Edit the variables below with your Plivo endpoint username and password
    public final static String PLIVO_USERNAME = "kalitk0160719110805"; 
    public final static String PLIVO_PASSWORD = "alabala";

    // Edit the PHONE_NUMBER with the number you want to make the call to
    public static String PHONE_NUMBER = "359878710758";
    private AudioManager myAudioManager;
    Endpoint endpoint = Endpoint.newInstance(true, this); 
    Outgoing outgoing = new Outgoing(endpoint);
	
    @Override 
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("Call".equals(action)) {
            String id = args.getString(0);
            this.callNow(id); 
             
            return true; 
        }
        if ("Login".equals(action)) {
        	Log.d("PLIVO", "LOGING");
            this.Login();   
            return true; 
        } 
        if ("Hangup".equals(action)) {
        	Log.d("PLIVO", "HANGUP);
            this.hangupNow();   
            return true; 
        } 
        if ("Speaker".equals(action)) {
        	Log.d("PLIVO", "SPEAKER");
            this.speakerOn();   
            return true; 
        }
        if ("Ear".equals(action)) {
        	Log.d("PLIVO", "EAR");
            this.speakerOff();   
            return true; 
        }
        if ("Mute".equals(action)) {
        	Log.d("PLIVO", "MUTE");
            this.mute();   
            return true; 
        }
        if ("Unmute".equals(action)) {
        	Log.d("PLIVO", "UNMUTE");
            this.unmute();   
            return true; 
        }
        if ("Logout".equals(action)) {
        	Log.d("PLIVO", "LOGOUT");
            this.Logout();   
            return true; 
        }
        return false;  
    }
    
    
    public void Login(){
    	Log.d("PLIVO", "LOGING");
    	endpoint.login(PLIVO_USERNAME, PLIVO_PASSWORD);
    	
    	
    }
    public void Logout(){
    	
    	endpoint.logout();
    	
    }
    
    public void hangupNow() {
        Log.v("PlivoOutbound", "Hanging up...");
        outgoing.hangup();
        
        this.webView.sendJavascript("plivoonoutgoingcallhangup()");
        
    }  
      
    public void callNow(String id){ 
    	
    	outgoing = endpoint.createOutgoingCall();
    	outgoing.setToContact("0878787878");
    	outgoing.setCallId("2323423423");
    	
    	outgoing.sendDigits("3"); 
    	Log.d("PLIVO",outgoing.getCallId()); 
    	
    	outgoing.call(id);
    } 
    
    public void speakerOn() {
        Log.v("PlivoOutbound", "Speaker on...");
        Context context = webView.getContext();
        myAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        
            myAudioManager.setSpeakerphoneOn(true);
        

    }
    
   
    
    public void speakerOff() {
        Log.v("PlivoOutbound", "Speaker off...");
        Context context = webView.getContext();
        myAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        
            myAudioManager.setSpeakerphoneOn(false);
        

    }
    
    public void mute() {
        Log.v("PlivoOutbound", "MUTE");
        outgoing.mute();
        

    }
    
    public void unmute() {
        Log.v("PlivoOutbound", "UNMUTE");
        outgoing.unmute();
        

    }
    
    
    @Override
	public void onIncomingCall(Incoming arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onIncomingCallHangup(Incoming arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onIncomingCallRejected(Incoming arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onLogin() {
		this.webView.sendJavascript("plivoonlogin()");
	//	CordovaWebView.this.sendJavascript(statememt);
		  
		Log.d("PLIVO", "LOGGEDIN"); 
		//fireDocumentEvent("onPlivoLogin");
		//javascriptCallback("onPlivoLogin", mInitCallbackContext);
	}    
	@Override      
	public void onLoginFailed() { 
		// TODO Auto-generated method stub 
		Log.d("PLIVO", "LOGGIN FAILED"); 
		this.webView.sendJavascript("plivoonloginfailed()");
	}
	@Override  
	public void onLogout() {   
		 
		//fireDocumentEvent("onPlivoLogin");
	//	javascriptCallback("onPlivoLogin", mInitCallbackContext);
		// TODO Auto-generated method stub
		Log.d("PLIVO", "LOGOUT");
		this.webView.sendJavascript("plivoonlogout()");
	//	sip.this.javascriptCallback("onPlivoLogout", mInitCallbackContext);
	}
	@Override
	public void onOutgoingCall(Outgoing arg0) {
		// TODO Auto-generated method stub
		Log.d("PLIVO", "CALLING");
		this.webView.sendJavascript("plivoonoutgoingcall()");
	}
	@Override
	public void onOutgoingCallAnswered(Outgoing arg0) {
		// TODO Auto-generated method stub
		Log.d("PLIVO", "ANSWERED");
		this.webView.sendJavascript("plivoonoutgoingcallanswered()");
	}
	@Override 
	public void onOutgoingCallHangup(Outgoing arg0) {
		// TODO Auto-generated method stub
		Log.d("PLIVO", "HANGUP");
		this.webView.sendJavascript("plivoonoutgoingcallhangup()");
	} 
	      
	       
	
	private void javascriptCallback(String event, JSONObject arguments,
			CallbackContext callbackContext) {
		if (callbackContext == null) { 
			return;  
		}   
		JSONObject options = new JSONObject(); 
		try {
			options.putOpt("callback", event);
			options.putOpt("arguments", arguments);
		} catch (JSONException e) {
			callbackContext.sendPluginResult(new PluginResult(
					PluginResult.Status.JSON_EXCEPTION));
			return;
		}
		PluginResult result = new PluginResult(Status.OK, options);
		result.setKeepCallback(true);
		callbackContext.sendPluginResult(result);

	}

	private void javascriptCallback(String event,
			CallbackContext callbackContext) {
		javascriptCallback(event, null, callbackContext);
	}
}