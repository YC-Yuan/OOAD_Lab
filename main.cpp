#include <iostream>

/**
 * 两种内容：标题，链接
 * 标题相当于文件夹，链接相当于文件，都属于可以添加的对象
 */

// 作为程序入口，处理命令行输入

int main() {
    std::cout << "Hello, World!" << std::endl;
    return 0;
}

/**
 * 读取cmd，分为add、delete、open、save、undo、redo、show-tree、ls-tree、read-bookmark
 * 需要支持redo、undo的指令：add、delete
 * 纯表现的指令：show-tree、ls-tree
 * 文件级指令：open、save
 * 灵活插件指令：read-bookmark
 */
class CmdReader {

};
