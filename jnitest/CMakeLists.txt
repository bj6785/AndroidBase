cmake_minimum_required(VERSION 3.4.1)

add_library( # 为library起名字
             native-lib

             # 设置为SHARE类型，STATIC为需要加载.a文件
             SHARED

             # 需要编译的源代码文件目录
             src/main/cpp/native-lib.cpp
             )

# 添加外部so库需要配置
# add_library( avcodec-57 # 为library起名字
#              SHARED
#              IMPORTED   # 不需要编译，由外部导入
#              )
# set_target_properties( avcodec-57
#                        PROPERTIES IMPORTED_LOCATION
#                        ../../../../libs/armeabi-v7a/libavcodec-57.so )//配置导入文件的路径

#外部库需要的头文件
include_directories( libs/include )

# 加载NDK上的library
# Searches for a specified prebuilt library and stores the path as a
# variable. Because system libraries are included in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# 将需要的库文件进行链接
# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
target_link_libraries( # Specifies the target library.
                       native-lib
                       # avcodec-57
                       # .....
                       # .....
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )
