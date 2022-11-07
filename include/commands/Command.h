//
// Created by 袁逸聪 on 2022/11/7.
//
#include <cstring>

#ifndef OOAD_LAB_COMMAND_H
#define OOAD_LAB_COMMAND_H
#endif //OOAD_LAB_COMMAND_H

/**
 * 基类，用于解析指令
 * 解析时，自动存储需要的参数用于执行
 */
class Command {
public:
    virtual bool Parse(char *cmd) = 0;

    virtual void Execute() = 0;
};

/**
 * 读取cmd，分为add、delete、open、save、undo、redo、show-tree、ls-tree、read-bookmark
 * 需要支持redo、undo的指令：add、delete
 * 纯表现的指令：show-tree、ls-tree
 * 文件级指令：open、save
 * 灵活插件指令：read-bookmark
 */
class CmdShowTree : public Command {
public:

};