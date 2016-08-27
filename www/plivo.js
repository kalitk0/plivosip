(function() {
    var delegate = {}
    var Plivo = {
        
        Connection: function() {
            return this;
        }
    }


   

    Plivo.Connection.prototype.login = function() {
       
            Cordova.exec(null,null,"Plivo","Login",[]);
    
    }
    
    Plivo.Connection.prototype.speaker = function() {
       
            Cordova.exec(null,null,"Plivo","Speaker",[]);
    
    }
    
    Plivo.Connection.prototype.ear = function() {
       
            Cordova.exec(null,null,"Plivo","Ear",[]);
    
    }
    
    Plivo.Connection.prototype.mute = function(argment) {
       
            Cordova.exec(null,null,"Plivo","Mute",[]);
    
    }
    
    Plivo.Connection.prototype.unmute = function() {
       
            Cordova.exec(null,null,"Plivo","Unmute",[]);
    
    }
    
    Plivo.Connection.prototype.hanguo = function() {
       
            Cordova.exec(null,null,"Plivo","Hangup",[]);
    
    }
    
    Plivo.Connection.prototype.call = function(argument) {
       
            Cordova.exec(null,null,"Plivo","Call",[argument]);
    
    }
    
    Plivo.Connection.prototype.onlogin = function(fn) {
        delegate['onPlivoLogin'] = fn;
    }

    

    Plivo.install = function() {
        if (!window.Plivo) window.Plivo = {};
       
        if (!window.Plivo.Connection) window.Plivo.Connection = new Plivo.Connection();
    }
 Plivo.install();

})()




