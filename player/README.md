### ijkplayer 编译
#### 1. 克隆 ijkplayer
    进入某个目录，将 ijkplayer 项目克隆下来
    > git clone https://github.com/bilibili/ijkplayer.git

    cd 进入 clone 的 ijkplayer 目录下，切换分支
    > git checkout -B latest k0.8.8

#### 2. 下载 FFmpeg 源码
    经过第一步切换分支后，在 ijkplayer 目录下执行如下指令；
    > ./init-android.sh

    不过中途可能会失败，提示：
    > fatal: 过早的文件结束符（EOF）
      fatal: index-pack 失败

    解决方式：
    > git config http.postBuffer 524288000

#### 3. 编译
> cd config/
> gedit module-lite.sh  添加
> cd ijkmedia/ijkplayer/  修改

> cd android/contrib/
> ./compile-ffmpeg.sh clean
> ./compile-ffmpeg.sh all

> cd ..
> ./compile-ijk.sh all