package com.example.marcin.nudzimisie;

/**
 * Created by Marcin on 2015-01-13.
 */

public class Constants {

    public static final String LOGIN_API_KEY = "login";
    public static final String PASSWORD_API_KEY = "password";
    public static final String SUCCESS_API_KEY = "success";
    public static final String MESSAGE_API_KEY = "message";
    public static final String EMAIL_API_KEY = "email"; // TYLKO DO USER TRANSFORM
    public static final String USERNAME_API_KEY = "username"; //TYLKO DO USER TRANSFORM


    public static final String SOCIALFOOD_URL = "http://socialfood.pl";
    public static final String LOGIN_URL = SOCIALFOOD_URL + "/mobile_apk/m_login.php";
    public static final String REGISTER_URL = SOCIALFOOD_URL + "/mobile_apk/register.php";
    public static final String READ_COMMENTS_URL = SOCIALFOOD_URL + "/mobile_apk/comments.php";
    public static final String POSTS_URL = SOCIALFOOD_URL + "/mobile_apk/comments.php";
    public static final String TRANSFORM_URL = SOCIALFOOD_URL + "/mobile_apk/userTransform.php";
    public static final String POSTS_JSON_ARRAY_URL = SOCIALFOOD_URL + "/mobile_apk/posts.php";
    public static final String POST_SEND_DATA_URL = SOCIALFOOD_URL + "/mobile_apk/send_post2.php";

    public static final int CAM_REQUEST = 1313;
    public static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    public static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    //SHARED PREFERENCES
    public static final String USER_DATA_PREF_FILE = "UserDataPrefFile" ;
    public static final String USERNAME_PREF_KEY = "UsernameKey";
    public static final String PASSWORD_SHA1_PREF_KEY = "PasswordKey";


    // File upload url (replace the ip with your server address)
    public static final String FILE_UPLOAD_URL = SOCIALFOOD_URL + "mobile_apk/fileUpload.php";

    // Directory name to store captured images and videos
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";

}
