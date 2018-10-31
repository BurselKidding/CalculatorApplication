package com.example.bursel.calculaterapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.widget.Button;
import android.view.View;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {




    public class Expression{
        public StringBuffer toPostfix(String infix){
            Stack<String> stack=new SeqStack<String>(infix.length());
            StringBuffer postfix=new StringBuffer(infix.length()*2);

            int i=0;
            while(i<infix.length()){
                char ch=infix.charAt(i);
                switch(ch){
                    case '+': case '-':
                        while(!stack.isEmpty()&&!stack.peek().equals("("))
                            postfix.append(stack.pop());
                        stack.push(ch+"");
                        i++;
                        break;
                    case '*': case '/':
                        while(!stack.isEmpty()&&(stack.peek().equals("*")||stack.peek().equals("/")))
                            postfix.append(stack.pop());
                        stack.push(ch+"");
                        i++;
                        break;
                    case '(':
                        stack.push(ch+"");
                        i++;
                        break;
                    case ')':
                        String out=stack.pop();
                        while(out!=null&&!out.equals("(")){
                            postfix.append(out);
                            out=stack.pop();
                        }
                        i++;
                        break;
                        default:
                            while(i<infix.length()&&ch>='0'&&ch<='9'||ch=='.'){
                                postfix.append(ch);
                                i++;
                                if(i<infix.length())
                                    ch=infix.charAt(i);
                            }
                            postfix.append(" ");
                }
            }
            while(!stack.isEmpty())
                postfix.append(stack.pop());
            return postfix;
        }
        public Double toValue(StringBuffer postfix){
            Stack<Double> stack =new LinkedStack<Double>();
            double value=0;

            for(int i=0;i<postfix.length();i++){
                char ch=postfix.charAt(i);
                if(ch>='0'&&ch<='9'||ch=='.'){
                    String number="";
                    value=0;
                    while(ch!=' '){
                        number+=ch;
                        ch=postfix.charAt(++i);
                    }
                    value=Double.parseDouble(number);
                    stack.push(value);                        //在栈中添加运算数
                }
                else if(ch!=' '){
                    double y=stack.pop(),x=stack.pop();
                    switch(ch){
                        case '+':value=x+y; break;
                        case '-':value=x-y; break;
                        case '*':value=x*y; break;
                        case '/':value=x/y; break;
                    }
                    //System.out.print("x+(ch+"")+y+"="+value+",");
                    stack.push(value);
                }
            }
            return stack.pop();
        }
    }

    boolean IS_DECIMAL=false;
    public boolean getIS_DECIMAL(){
        return IS_DECIMAL;
    }
    public void setIS_DECIMAL(boolean isDecimal){
        IS_DECIMAL=isDecimal;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //回退和清屏按钮
        Button btnDelAll=(Button)findViewById(R.id.delAll);
        Button btnDel=(Button)findViewById(R.id.Del);
        //左右括号按钮
        Button btnOpParenthesis=(Button)findViewById(R.id.opParenthesis);
        Button btnClsParenthesis=(Button)findViewById(R.id.clsParenthesis);
        //运算符按钮
        Button btnPlus=(Button)findViewById(R.id.Plus);
        Button btnSub=(Button)findViewById(R.id.Sub);
        Button btnMultiple=(Button)findViewById(R.id.Multiple);
        Button btnDivide=(Button)findViewById(R.id.Divide);
        Button btnEquls=(Button)findViewById(R.id.Equals);
        //数值按钮
        Button btnPoint=(Button)findViewById(R.id.Point);
        Button btnZero=(Button)findViewById(R.id.Zero);
        Button btnOne=(Button)findViewById(R.id.One);
        Button btnTwo=(Button)findViewById(R.id.Two);
        Button btnThree=(Button)findViewById(R.id.Three);
        Button btnFour=(Button)findViewById(R.id.Four);
        Button btnFive=(Button)findViewById(R.id.Five);
        Button btnSix=(Button)findViewById(R.id.Six);
        Button btnSeven=(Button)findViewById(R.id.Seven);
        Button btnEight=(Button)findViewById(R.id.Eight);
        Button btnNight=(Button)findViewById(R.id.Night);
        //设置所有按钮的监听器
        btnDelAll.setOnClickListener(new ButtonListener());
        btnDel.setOnClickListener(new ButtonListener());
        btnOpParenthesis.setOnClickListener(new ButtonListener());
        btnClsParenthesis.setOnClickListener(new ButtonListener());
        btnPlus.setOnClickListener(new ButtonListener());
        btnSub.setOnClickListener(new ButtonListener());
        btnMultiple.setOnClickListener(new ButtonListener());
        btnDivide.setOnClickListener(new ButtonListener());
        btnEquls.setOnClickListener(new ButtonListener());
        btnZero.setOnClickListener(new ButtonListener());
        btnOne.setOnClickListener(new ButtonListener());
        btnTwo.setOnClickListener(new ButtonListener());
        btnThree.setOnClickListener(new ButtonListener());
        btnFour.setOnClickListener(new ButtonListener());
        btnFive.setOnClickListener(new ButtonListener());
        btnSix.setOnClickListener(new ButtonListener());
        btnSeven.setOnClickListener(new ButtonListener());
        btnEight.setOnClickListener(new ButtonListener());
        btnNight.setOnClickListener(new ButtonListener());
        btnPoint.setOnClickListener(new ButtonListener());

    }

    private class ButtonListener implements View.OnClickListener{


        public void onClick(View v){
            TextView outputText=(TextView)findViewById(R.id.outputText);
            int textLength=outputText.getText().length();
            String lastChar="";
            if(textLength==0){
                lastChar="";
            }
            else{
                lastChar=outputText.getText().subSequence(textLength-1,textLength).toString();
            }
            switch(v.getId()){
                case R.id.delAll:
                    outputText.setText("");
                    break;
                case R.id.Del:
                    if(outputText.getText().length()>0){
                        outputText.setText(outputText.getText().subSequence(0,textLength-1));
                        break;
                    }
                    else {
                        break;
                    }
                case R.id.opParenthesis:
                    outputText.setText(outputText.getText()+"(");
                    break;
                case R.id.clsParenthesis:
                    outputText.setText(outputText.getText()+")");
                    break;
                case R.id.Zero:
                    outputText.setText(outputText.getText()+"0");
                    break;
                case R.id.One:
                    outputText.setText(outputText.getText()+"1");
                    break;
                case R.id.Two:
                    outputText.setText(outputText.getText()+"2");
                    break;
                case R.id.Three:
                    outputText.setText(outputText.getText()+"3");
                    break;
                case R.id.Four:
                    outputText.setText(outputText.getText()+"4");
                    break;
                case R.id.Five:
                    outputText.setText(outputText.getText()+"5");
                    break;
                case R.id.Six:
                    outputText.setText(outputText.getText()+"6");
                    break;
                case R.id.Seven:
                    outputText.setText(outputText.getText()+"7");
                    break;
                case R.id.Eight:
                    outputText.setText(outputText.getText()+"8");
                    break;
                case R.id.Night:
                    outputText.setText(outputText.getText()+"9");
                    break;
                case R.id.Plus:
                    if(isOperator(lastChar)==true||lastChar.equals(".")){
                        outputText.setText(outputText.getText().subSequence(0,textLength-1)+"+");
                        setIS_DECIMAL(false);
                        break;
                    }
                    else {
                        outputText.setText(outputText.getText()+"+");
                        setIS_DECIMAL(false);
                        break;
                    }
                case R.id.Sub:
                    if(isOperator(lastChar)==true||lastChar.equals(".")){
                        outputText.setText(outputText.getText().subSequence(0,textLength-1)+"-");
                        setIS_DECIMAL(false);
                        break;
                    }
                    else {
                        outputText.setText(outputText.getText()+"-");
                        setIS_DECIMAL(false);
                        break;
                    }

                case R.id.Multiple:
                    if(isOperator(lastChar)==true||lastChar.equals(".")){
                        outputText.setText(outputText.getText().subSequence(0,textLength-1)+"*");
                        setIS_DECIMAL(false);
                        break;
                    }
                    else {
                        outputText.setText(outputText.getText()+"*");
                        setIS_DECIMAL(false);
                        break;
                    }
                case R.id.Divide:
                    if(isOperator(lastChar)==true||lastChar.equals(".")){
                        outputText.setText(outputText.getText().subSequence(0,textLength-1)+"/");
                        setIS_DECIMAL(false);
                        break;
                    }
                    else {
                        outputText.setText(outputText.getText()+"/");
                        setIS_DECIMAL(false);
                        break;
                    }
                case R.id.Point:
                    if(isOperator(lastChar)==true||lastChar.equals(".")){
                        break;
                    }
                    else{
                        if(IS_DECIMAL==false){
                            outputText.setText(outputText.getText()+".");
                            setIS_DECIMAL(true);
                            break;
                        }
                        else{
                            break;
                        }
                    }
                case R.id.Equals:
                    Expression expression=new Expression();
                    String infix=outputText.getText().toString();
                    StringBuffer postfix=expression.toPostfix(infix);
                    outputText.setText(outputText.getText().toString()+"="+String.valueOf(expression.toValue(postfix)));
                    break;
            }
        }
        public boolean isOperator(String testChar){
            if(testChar.equals("+")||testChar.equals("-")||testChar.equals("*")||testChar.equals("/")){
                return true;
            }
            else{
                return false;
            }
        }
    }


}
