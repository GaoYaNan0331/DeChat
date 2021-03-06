package com.isabella.dechat.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.isabella.dechat.R;
import com.isabella.dechat.base.AppManager;
import com.isabella.dechat.base.BaseActivity;
import com.isabella.dechat.base.IApplication;
import com.isabella.dechat.bean.UploadPhotoBean;
import com.isabella.dechat.contact.UploadContact;
import com.isabella.dechat.presenter.UploadPresenter;
import com.isabella.dechat.util.Constants;
import com.isabella.dechat.util.DialogUtils;
import com.isabella.dechat.util.GlideUtils;
import com.isabella.dechat.util.ImageResizeUtils;
import com.isabella.dechat.util.NetUtil;
import com.isabella.dechat.util.PreferencesUtils;
import com.isabella.dechat.util.SDCardUtils;
import com.isabella.dechat.widget.MyToast;
import com.jakewharton.rxbinding2.view.RxView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

import static com.isabella.dechat.util.ImageResizeUtils.copyStream;

/**
 * description
 * Created by 张芸艳 on 2017/7/6.
 */
@RuntimePermissions
public class UploadActivity extends BaseActivity<UploadContact.UploadView, UploadPresenter> implements UploadContact.UploadView {
    @BindView(R.id.login_back)
    ImageView loginBack;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.upload_head)
    ImageView uploadHead;
    @BindView(R.id.upload_camera)
    Button uploadCamera;
    @BindView(R.id.upload_photo)
    Button uploadPhoto;
    @BindView(R.id.upload_sure)
    Button uploadSure;
    @BindView(R.id.upload_over)
    ImageView uploadOver;
    @BindView(R.id.upload_set_head)
    TextView uploadSetHead;
    private UploadPresenter uploadPresenter = new UploadPresenter();
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                if (from == 0) {
                    toActivity(MainActivity.class, null, 0);
                    PreferencesUtils.addConfigInfo(UploadActivity.this, "isSetPhoto", temp);
                    AppManager.getAppManager().finishOtherActivity();
                    AppManager.getAppManager().finishActivity(SplashActivity.class);
                    AppManager.getAppManager().finishActivity(RegisterActivity.class);
                    AppManager.getAppManager().finishActivity(PhoneRegisterActivity.class);
                    AppManager.getAppManager().finishActivity(IntroActivity.class);
                }else{
                    finish();
                }



            }
        }

    };
    private int from;
    private AlertDialog.Builder builder;
    private boolean temp = false;
    private boolean success = false;
    private AlertDialog.Builder dialogLogin;

    @Override
    public UploadPresenter initPresenter() {
        return uploadPresenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);
        from = PreferencesUtils.getValueByKey(IApplication.getApplication(), "from", 0);

        String age = PreferencesUtils.getValueByKey(this, "gender", "");
        Log.d("UploadActivity", age);
        if ("男".equals(age)) {
            uploadHead.setImageResource(R.drawable.man_user_round_icon_default);
        } else {
            uploadHead.setImageResource(R.drawable.woman_user_round_icon_default);
        }
        if (from == 1) {
            uploadSetHead.setText("上传相册");
            uploadHead.setImageResource(R.drawable.ic_album_default);
        } else {
            uploadSetHead.setText("设置头像");
        }
        builder = DialogUtils.setDialog(this);
        dialogLogin = DialogUtils.setDialogLogin(this);
        RxView.clicks(uploadCamera).throttleFirst(1, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (NetUtil.isNetworkAvailable(UploadActivity.this)) {
                            toCheckPermissionCamera();
                            temp = true;
                        } else {
                            UploadActivity.this.builder.show();
                        }

                    }
                });
        RxView.clicks(uploadPhoto).throttleFirst(1, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        if (NetUtil.isNetworkAvailable(UploadActivity.this)) {
                            toPhoto();
                            temp = true;
                        } else {
                            UploadActivity.this.builder.show();
                        }

                    }
                });
        RxView.clicks(uploadSure).throttleFirst(3, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        loginBack.setClickable(false);
                        uploadCamera.setClickable(false);
                        uploadPhoto.setClickable(false);
                        uploadSure.setClickable(false);
                        uploadOver.setVisibility(View.VISIBLE);
                        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                        scaleAnimation.setDuration(2000);
                        uploadOver.startAnimation(scaleAnimation);
                        handler.sendEmptyMessageDelayed(0, 2000);


                    }
                });
    }

    @OnClick({R.id.login_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_back:
                if (from == 0) {
                    toActivity(IntroActivity.class, null, 0);
                }
                finish();
                break;

        }
    }

    static final int INTENTFORCAMERA = 1;
    static final int INTENTFORPHOTO = 2;


    public String LocalPhotoName;

    public String createLocalPhotoName() {
        LocalPhotoName = System.currentTimeMillis() + "face.jpg";
        return LocalPhotoName;
    }

    /**
     * 打开系统相机
     */
    @NeedsPermission({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    public void toCamera() {
        try {
            Intent intentNow = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intentNow.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(SDCardUtils.getMyFaceFile(createLocalPhotoName())));
            startActivityForResult(intentNow, INTENTFORCAMERA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnShowRationale({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    public void showRationaleForCamera(final PermissionRequest request) {

        new AlertDialog.Builder(this)
                .setMessage("需要打开您的相机来上传照片并保存照片")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        request.proceed();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .show();
    }


    @OnPermissionDenied({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    public void onDenied() {
        MyToast.getInstance().makeText( "权限被拒绝");

    }


    @OnNeverAskAgain({Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA})
    public void onNeverAsyAgain() {
        MyToast.getInstance().makeText( "不再提示");
    }


    /**
     * 打开相册
     */
    public void toPhoto() {
        try {
            createLocalPhotoName();
            Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
            getAlbum.setType("image/*");
            startActivityForResult(getAlbum, INTENTFORPHOTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void toCheckPermissionCamera() {

        UploadActivityPermissionsDispatcher.toCameraWithCheck(this);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        UploadActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case INTENTFORPHOTO:
                //相册

                try {
                    // 必须这样处理，不然在4.4.2手机上会出问题
                    Uri originalUri = data.getData();
                    File f = null;
                    if (originalUri != null) {
                        f = new File(SDCardUtils.photoCacheDir, LocalPhotoName);
                        String[] proj = {MediaStore.Images.Media.DATA};
                        Cursor actualimagecursor = this.getContentResolver().query(originalUri, proj, null, null, null);
                        if (null == actualimagecursor) {
                            if (originalUri.toString().startsWith("file:")) {
                                File file = new File(originalUri.toString().substring(7, originalUri.toString().length()));
                                if (!file.exists()) {
                                    //地址包含中文编码的地址做utf-8编码
                                    originalUri = Uri.parse(URLDecoder.decode(originalUri.toString(), "UTF-8"));
                                    file = new File(originalUri.toString().substring(7, originalUri.toString().length()));
                                }
                                FileInputStream inputStream = new FileInputStream(file);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            }
                        } else {
                            // 系统图库
                            int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                            actualimagecursor.moveToFirst();
                            String img_path = actualimagecursor.getString(actual_image_column_index);
                            if (img_path == null) {
                                InputStream inputStream = this.getContentResolver().openInputStream(originalUri);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            } else {
                                File file = new File(img_path);
                                FileInputStream inputStream = new FileInputStream(file);
                                FileOutputStream outputStream = new FileOutputStream(f);
                                copyStream(inputStream, outputStream);
                            }

                        }
                        Bitmap bitmap = ImageResizeUtils.resizeImage(f.getAbsolutePath(), Constants.RESIZE_PIC);

                        FileOutputStream fos = new FileOutputStream(f.getAbsolutePath());
                        if (bitmap != null) {
                            PreferencesUtils.addConfigInfo(this, "picwidth", bitmap.getWidth() + "");
                            Log.d("UploadActivity", "bitmap.getWidth():" + bitmap.getWidth());
                            Log.d("UploadActivity", "bitmap.getHeight():" + bitmap.getHeight());
                            PreferencesUtils.addConfigInfo(this, "picheight", bitmap.getHeight() + "");
                            if (from == 0) {
                                GlideUtils.getInstance().bitmapRound(bitmap, this, uploadHead);
                            } else {
                                GlideUtils.getInstance().bitmapSet(bitmap, this, uploadHead);
                            }
                            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)) {
                                fos.close();
                                fos.flush();
                            }
                            if (!bitmap.isRecycled()) {
                                bitmap.isRecycled();
                            }

                            uploadFile(f);

                        }

                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }


                break;
            case INTENTFORCAMERA:
//                相机
                try {

                    //file 就是拍照完 得到的原始照片
                    File file = new File(SDCardUtils.photoCacheDir, LocalPhotoName);
                    Bitmap bitmap = ImageResizeUtils.resizeImage(file.getAbsolutePath(), Constants.RESIZE_PIC);
                    FileOutputStream fos = new FileOutputStream(file.getAbsolutePath());
                    if (bitmap != null) {
                        PreferencesUtils.addConfigInfo(this, "picwidth", bitmap.getWidth() + "");
                        PreferencesUtils.addConfigInfo(this, "picheight", bitmap.getHeight() + "");
                        if (from == 0) {
                            GlideUtils.getInstance().bitmapRound(bitmap, this, uploadHead);
                        } else {
                            GlideUtils.getInstance().bitmapSet(bitmap, this, uploadHead);
                        }
                        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fos)) {
                            fos.close();
                            fos.flush();
                        }
                        if (!bitmap.isRecycled()) {
                            //通知系统 回收bitmap
                            bitmap.isRecycled();
                        }
                        uploadFile(file);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
        }


    }


    public void uploadFile(File file) {


        if (!file.exists()) {
            MyToast.getInstance().makeText(  " 照片不存在");
            return;
        }
        presenter.getData(file);

    }

    @Override
    public void success(UploadPhotoBean uploadPhotoBean) {

        if (uploadPhotoBean.getResult_code()==301){

            IApplication.setIsLogin(false);
            dialogLogin.show();
        }else {

            // handler.sendEmptyMessage(1);
            MyToast.getInstance().makeText( "上传成功");
            if (from == 0) {
                PreferencesUtils.addConfigInfo(this, "imagepath", uploadPhotoBean.getHeadImagePath());
            }
        }
    }

    @Override
    public void failed(Throwable e) {
        MyToast.getInstance().makeText(  "上传失败");
        Log.d("UploadActivity", "e:" + e);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        if (builder!=null){
            builder=null;
        }
        if (dialogLogin!=null){
            builder=null;
        }
    }
}

