var OAUTH2_CLIENT_ID = 'AIzaSyBAqP5bT_zfE1bfGly5-Ii6_2_V80JzpQE';
function loadAPIClientInterfaces() {
	gapi.client.setApiKey(OAUTH2_CLIENT_ID);
	gapi.client.load('youtube', 'v3', function() {
    //handleAPILoaded();
  });
}