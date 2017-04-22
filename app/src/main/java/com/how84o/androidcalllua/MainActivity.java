package com.how84o.androidcalllua;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

//这里导入的就是第一步复制到main/java文件夹下的包
import org.keplerproject.luajava.LuaState;
import org.keplerproject.luajava.LuaStateFactory;
import org.keplerproject.luajava.LuaObject;


public class MainActivity extends AppCompatActivity {
    private LuaState m_LuaStateMachine;//Lua解析和执行由此对象完成

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建LUA状态机
        m_LuaStateMachine = LuaStateFactory.newLuaState();
        m_LuaStateMachine.openLibs();


        //runLuaScriptStringDirectly();
        //callLuaFunction_0_1();
        //callLuaFunction_1_1();
       // callLuaFunction_1_2();

        //callLuaFunction_2_2();

        callLuaFunction_2_1();

    }
    private void runLuaScriptStringDirectly()
    {
        //直接执行LUA代码
        m_LuaStateMachine.LdoString(" varSay = 'call from android : This is string in lua script statement.'");// 定义一个Lua变量
        m_LuaStateMachine.getGlobal("varSay");// 获取全局变量值
        Log.d("MainActivity", "LUA输出："+ m_LuaStateMachine.toString(-1));
    }

    //调用0个输入参数。1个返回值的LUA函数
    private void callLuaFunction_0_1()
    {
        m_LuaStateMachine.LdoString("function luaFuncName()\n" +
                "\treturn \"in lua\"\n" +
                "end");

        //此处调用的函数就是LdoString()加载进来的LUA代码中的函数
        m_LuaStateMachine.getField(LuaState.LUA_GLOBALSINDEX, "luaFuncName");//获取lua代码中的函数

        //调用函数。指定luaFuncName的形参个数和返回值个数
        m_LuaStateMachine.call(0 /*形参个数*/,  1 /*返回值个数*/);

        // 令其将结果保存到retVal变量中
        m_LuaStateMachine.setField(LuaState.LUA_GLOBALSINDEX, "retVal");
        m_LuaStateMachine.getGlobal("retVal");

        //取出返回值
        Log.d("MainActivity", "调用LUA函数返回值：："+ m_LuaStateMachine.toString(-1));
    }
    //调用1个输入参数。1个返回值的LUA函数
    private void callLuaFunction_1_1()
    {
        m_LuaStateMachine.LdoString("function luaFuncName(input)\n" +
                "\treturn \"in lua\" .. input \n" +
                "end");

        //此处调用的函数就是LdoString()加载进来的LUA代码中的函数
        m_LuaStateMachine.getField(LuaState.LUA_GLOBALSINDEX, "luaFuncName");//获取lua代码中的函数

        //传入参数。pushInteger、pushString等函数，可传入各种参数
         m_LuaStateMachine.pushString(" InputString_Java");

        //调用函数。指定luaFuncName的形参个数和返回值个数
        m_LuaStateMachine.call(1 /*形参个数*/,  1 /*返回值个数*/);

        // 令其将结果保存到retVal变量中
        m_LuaStateMachine.setField(LuaState.LUA_GLOBALSINDEX, "retVal");

        //LuaObject obj = m_LuaStateMachine.getLuaObject("retVal");
        m_LuaStateMachine.getGlobal("retVal");

        //取出返回值
        Log.d("MainActivity", "调用LUA函数返回值：："+ m_LuaStateMachine.toString(-1));

    }
    //调用1个输入参数。2个返回值的LUA函数
    private void callLuaFunction_1_2()
    {
        m_LuaStateMachine.LdoString("function luaFuncName(input)\n" +
                "\treturn \"in lua\" .. input1,\" input2\" \n" +
                "end");

        //此处调用的函数就是LdoString()加载进来的LUA代码中的函数
        m_LuaStateMachine.getField(LuaState.LUA_GLOBALSINDEX, "luaFuncName");//获取lua代码中的函数

        //传入参数。pushInteger、pushString等函数，可传入各种参数
        m_LuaStateMachine.pushString(" InputString_Java");

        //调用函数。指定luaFuncName的形参个数和返回值个数
        m_LuaStateMachine.call(1 /*形参个数*/,  2 /*返回值个数*/);

        // 令其将结果保存到retVal变量中
        m_LuaStateMachine.setField(LuaState.LUA_GLOBALSINDEX, "retVal");

        //LuaObject obj = m_LuaStateMachine.getLuaObject("retVal");
        m_LuaStateMachine.getGlobal("retVal");


        //取出返回值
        //LUA函数的return语句中，越是写在后面的返回值，越是在栈顶。所以 m_LuaStateMachine.toXXX()系列函数中的索引值越小。
        //有更多返回值时同理能取出各种类型的所有返回值
        Log.d("MainActivity", "调用LUA函数第1个返回值：："+ m_LuaStateMachine.toString(-2));
        Log.d("MainActivity", "调用LUA函数第2个返回值：："+ m_LuaStateMachine.toString(-1));
    }

    //调用2个输入参数。2个返回值的LUA函数
    private void callLuaFunction_2_2()
    {
        m_LuaStateMachine.LdoString("function luaFuncName(input1,input2)\n" +
                "\treturn \"in lua return: \" .. input1,\" input2\" \n" +
                "end");

        //此处调用的函数就是LdoString()加载进来的LUA代码中的函数
        m_LuaStateMachine.getField(LuaState.LUA_GLOBALSINDEX, "luaFuncName");//获取lua代码中的函数

        //传入参数。pushInteger、pushString等函数，可传入各种参数
        m_LuaStateMachine.pushString("param1");
        m_LuaStateMachine.pushString("param2");

        //调用函数。指定luaFuncName的形参个数和返回值个数
        m_LuaStateMachine.call(2 /*形参个数*/,  2 /*返回值个数*/);

        // 令其将结果保存到retVal变量中
        m_LuaStateMachine.setField(LuaState.LUA_GLOBALSINDEX, "retVal");

        //LuaObject obj = m_LuaStateMachine.getLuaObject("retVal");
        m_LuaStateMachine.getGlobal("retVal");


        //取出返回值
        //LUA函数的return语句中，越是写在后面的返回值，越是在栈顶。所以 m_LuaStateMachine.toXXX()系列函数中的索引值越小。
        //有更多返回值时同理能取出各种类型的所有返回值
        Log.d("MainActivity", "调用LUA函数第1个返回值：："+ m_LuaStateMachine.toString(-2));
        Log.d("MainActivity", "调用LUA函数第2个返回值：："+ m_LuaStateMachine.toString(-1));
    }
    private void callLuaFunction_2_1()
    {
        m_LuaStateMachine.LdoString("function luaFuncName(p1, p2)\n" +
                "\treturn p1..p2\n" +
                "end");

        //此处调用的函数就是LdoString()加载进来的LUA代码中的函数
        m_LuaStateMachine.getField(LuaState.LUA_GLOBALSINDEX, "luaFuncName");//获取lua代码中的函数

        //传入参数。pushInteger、pushString等函数，可传入各种参数
        m_LuaStateMachine.pushString("param1");
        m_LuaStateMachine.pushString("param2");

        //调用函数。指定luaFuncName的形参个数和返回值个数
        m_LuaStateMachine.call(2 /*形参个数*/,  1 /*返回值个数*/);

        // 令其将结果保存到retVal变量中
        m_LuaStateMachine.setField(LuaState.LUA_GLOBALSINDEX, "retVal");

        //LuaObject obj = m_LuaStateMachine.getLuaObject("retVal");
        m_LuaStateMachine.getGlobal("retVal");
        Log.d("MainActivity", "调用LUA函数返回值：："+ m_LuaStateMachine.toString(-1));
    }
}
