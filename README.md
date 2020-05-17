### ijkplayer 编译
&emsp;&emsp;目前 ijkplayer 支持的编译环境应该只有 Linux 或 Mac，Windows 用户可以直接到 [Github](https://github.com/Jin-Yx/LiveDemo/) 下载已经编译好的 so
**1. 克隆 ijkplayer 项目**
> git clone https://github.com/bilibili/ijkplayer.git

**2. 进入 ijkplayer 目录，切换到最新分支**
> cd ijkplayer
git checkout -B latest k0.8.8

**3. 下载 ffmpeg 依赖库**
> ./init-android.sh

&emsp;&emsp; 这一步会去 clone 需要的 ffmpeg 仓库，大概 200 多M 内容，需要等待一段时间，中途可能会出现如下错误导致 clone 失败
>fatal: 过早的文件结束符（EOF）
fatal: index-pack 失败

&emsp;&emsp; 据说是 clone 的项目文件太大，git 传输大小限制，修改一下即可
> git config http.postBuffer 524288000

**4. 修改配置文件**
&emsp;&emsp;进入 config 目录，下面有四个 .sh 文件，编译时读取的是 module.sh，不过默认 module-lite.sh 映射到 module.sh 上了，所以修改 module-lite.sh 即可；
> cd config
vim module-lite.sh

&emsp;&emsp; 打开 module-lite.sh 添加和修改内容如下
>export COMMON_FF_CFG_FLAGS="\$COMMON_FF_CFG_FLAGS --enable-demuxer=rtsp"
export COMMON_FF_CFG_FLAGS="\$COMMON_FF_CFG_FLAGS --enable-demuxer=sdp"
export COMMON_FF_CFG_FLAGS="\$COMMON_FF_CFG_FLAGS --enable-demuxer=rtp"

![图1. rtmp 配置](https://upload-images.jianshu.io/upload_images/3235212-f3541a34b274daf5.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![图2. rtsp 配置](https://upload-images.jianshu.io/upload_images/3235212-63a243474fda2865.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

如果 修改另外两个文件，需要修改关联
> ln -s 修改的module***.sh module.sh

**5. 编译**
&emsp;&emsp; 进入 ../ijkplayer/android/contrib/ 目录，开始编译，需要配置有  [ANDROID_NDK](https://developer.android.google.cn/ndk/downloads/revision_history.html?hl=zh-cn)  环境（NDKr10e or later）
> cd ..
cd android/contrib/
./compile-ffmpeg.sh clean
./compile-ffmpeg.sh all

&emsp;&emsp; 编译完成后回到 ../ijkplayer/android/ 目录下，继续执行下面指令，完成之后，在 ijkplayer/android/ijkplayer/ 目录下有对应各个平台的依赖库
>cd ..
./compile-ijk.sh all