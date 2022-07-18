package com.j4c08.calcu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Base extends AppCompatActivity {

    @BindView(R.id.output)
    TextView output;

    @BindView(R.id.currentOperation)
    TextView currentOperationText;

    private boolean isPerformingOperation;
    private double currentValue;
    private boolean isCeActive;
    private EArithmeticOperation currentOperation = EArithmeticOperation.BLANK;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        //restoring view values in case of rotation or activity kill
        if(savedInstanceState !=null) {
            this.output.setText(savedInstanceState.getString("output"));
            this.currentOperation = (EArithmeticOperation)savedInstanceState.getSerializable("currentOperation");
            this.currentValue = savedInstanceState.getDouble("currentValue");
            this.isCeActive = savedInstanceState.getBoolean("isCeActive");
        } else {
            this.output.setText("0");
        }
    }

    @OnClick(R.id.zero)
    public void OnClickZero(Button zero) {
        if(!isOutputEmpty()) {
            if(this.isPerformingOperation) {
                this.output.setText("0");
                this.isPerformingOperation = false;
            } else {
                this.output.append("0");
            }
        }
    }

    @OnClick(R.id.one)
    public void onClickOne(Button one) {
        appendNumber("1");
    }

    @OnClick(R.id.two)
    public void onClickTwo(Button two) {
        appendNumber("2");
    }

    @OnClick(R.id.three)
    public void onClickThree(Button three) {
        appendNumber("3");
    }

    @OnClick(R.id.four)
    public void onClickFour(Button four) {
        appendNumber("4");
    }

    @OnClick(R.id.five)
    public void onClickFive(Button five) {
        appendNumber("5");
    }

    @OnClick(R.id.six)
    public void onClickSix(Button six) {
        appendNumber("6");
    }

    @OnClick(R.id.seven)
    public void onClickSeven(Button seven) {
        appendNumber("7");
    }

    @OnClick(R.id.eight)
    public void onClickEight(Button eight) {
        appendNumber("8");
    }

    @OnClick(R.id.nine)
    public void onClickNine(Button nine) {
        appendNumber("9");
    }

    @OnClick(R.id.dot)
    public void onClickDot(Button dot) {
        if(!this.output.getText().toString().contains(".")) {
            this.output.append(".");
        }
    }

    @OnClick(R.id.plusMinus)
    public void onClickPlusMinus(Button plusMinus) {
        if(!output.getText().toString().equals("0")) {
            double outputNumber = Double.parseDouble(this.output.getText().toString());
            this.output.setText(String.valueOf(-outputNumber));
            setIntegerIfPossible();
        }
    }


    @OnClick(R.id.ce)
    public void onClickCe(Button ce) {
        if(isCeActive && Double.parseDouble(output.getText().toString()) == 0) {
            this.currentValue = 0;
            this.currentOperation = EArithmeticOperation.BLANK;
            this.isPerformingOperation = false;
            this.isCeActive = false;
            this.clearCurrentOperationLabel();
        } else {
            this.output.setText("0");
            this.isCeActive = true;
            this.isPerformingOperation = false;
        }
    }

    @OnClick(R.id.ac)
    public void onClickAc(Button clear) {
        this.isCeActive = false;
        this.allClear();
    }

    @OnClick(R.id.plus)
    public void onClickPlus(Button plus) {
        this.isCeActive = false;
        this.currentOperation = EArithmeticOperation.ADD;
        setCurrentOperationLabel();
        if(!isPerformingOperation && currentValue != 0) {
            double oldValue = currentValue;
            currentValue += Double.parseDouble(output.getText().toString());

            output.setText(String.valueOf(oldValue + Double.parseDouble(this.output.getText().toString())));
            setIntegerIfPossible();

            this.isPerformingOperation = true;
        } else {
            isPerformingOperation = true;
            this.currentValue = Double.parseDouble(this.output.getText().toString());
        }
    }


    @OnClick(R.id.minus)
    public void onClickMinus(Button subtract) {
        this.isCeActive = false;
        this.currentOperation = EArithmeticOperation.SUBTRACT;
        setCurrentOperationLabel();
        if(!isPerformingOperation && currentValue != 0) {
            double oldValue = currentValue;
            currentValue -= Double.parseDouble(output.getText().toString());

            output.setText(String.valueOf(oldValue - Double.parseDouble(this.output.getText().toString())));
            setIntegerIfPossible();

            this.isPerformingOperation = true;
        } else {
            isPerformingOperation = true;
            this.currentValue = Double.parseDouble(this.output.getText().toString());
        }
    }


    @OnClick(R.id.divide)
    public void onClickDivide(Button divide) {
        this.isCeActive = false;
        this.currentOperation = EArithmeticOperation.DIVIDE;
        setCurrentOperationLabel();
        if(!isPerformingOperation && currentValue != 0) {
            if(Double.parseDouble(output.getText().toString()) == 0.0) {
                Toast.makeText(this, "You can't divide by 0", Toast.LENGTH_SHORT).show();
            } else {
                double oldValue = currentValue;
                currentValue /= Double.parseDouble(output.getText().toString());

                output.setText(String.valueOf(oldValue / Double.parseDouble(this.output.getText().toString())));
                setIntegerIfPossible();

                this.isPerformingOperation = true;
            }
        } else {
            isPerformingOperation = true;
            this.currentValue = Double.parseDouble(this.output.getText().toString());
        }
    }

    @OnClick(R.id.multiply)
    public void onMultiply(Button multiply) {
        this.isCeActive = false;
        this.currentOperation = EArithmeticOperation.MULTIPLY;
        setCurrentOperationLabel();
        if(!isPerformingOperation && currentValue != 0) {
            double oldValue = currentValue;
            currentValue *= Double.parseDouble(output.getText().toString());

            output.setText(String.valueOf(oldValue * Double.parseDouble(this.output.getText().toString())));
            setIntegerIfPossible();

            this.isPerformingOperation = true;
        } else {
            isPerformingOperation = true;
            this.currentValue = Double.parseDouble(this.output.getText().toString());
        }
    }

    @OnClick(R.id.count)
    public void onEquals(Button equals) {
        this.isCeActive = false;
        if(this.currentOperation != null) {
            switch(this.currentOperation) {
                case ADD: {
                    if(!isPerformingOperation) {
                        this.output.setText(String.valueOf(this.currentValue
                                + Double.parseDouble(this.output.getText().toString())));
                        setIntegerIfPossible();

                        this.isPerformingOperation = true;
                        this.currentOperation = EArithmeticOperation.BLANK;
                        this.currentOperation = null;
                        this.currentValue = 0;
                        this.clearCurrentOperationLabel();
                    }
                    break;
                } case SUBTRACT: {
                    if(!isPerformingOperation) {
                        this.output.setText(String.valueOf(this.currentValue
                                - Double.parseDouble(this.output.getText().toString())));
                        setIntegerIfPossible();

                        this.isPerformingOperation = true;
                        this.currentOperation = EArithmeticOperation.BLANK;
                        this.currentValue = 0;
                        this.clearCurrentOperationLabel();
                    }
                    break;
                } case MULTIPLY: {
                    if (!isPerformingOperation) {
                        this.output.setText(String.valueOf(this.currentValue
                                * Double.parseDouble(this.output.getText().toString())));
                        setIntegerIfPossible();

                        this.isPerformingOperation = true;
                        this.currentOperation = EArithmeticOperation.BLANK;
                        this.currentValue = 0;
                        this.clearCurrentOperationLabel();
                    }
                    break;
                } case DIVIDE: {
                    if(!isPerformingOperation) {
                        if(Double.parseDouble(this.output.getText().toString()) == 0.0) {
                            Toast.makeText(this, "You can't divide by 0", Toast.LENGTH_SHORT).show();
                        } else {
                            this.output.setText(String.valueOf(this.currentValue
                                    / Double.parseDouble(this.output.getText().toString())));
                            setIntegerIfPossible();

                            this.isPerformingOperation = true;
                            this.currentOperation = EArithmeticOperation.BLANK;
                            this.currentValue = 0;
                            this.clearCurrentOperationLabel();
                        }
                    }
                    break;
                } default: {
                    break;
                }
            }
        }
    }

    //saving state of calculator in case of destruction
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("output", this.output.getText().toString());
        outState.putSerializable("currentOperation", this.currentOperation);
        outState.putDouble("currentValue", this.currentValue);
        outState.putBoolean("isPerformingOperation", this.isPerformingOperation);
        outState.putBoolean("isCeActive", this.isCeActive);

        super.onSaveInstanceState(outState);
    }

    private boolean isOutputEmpty() {
        return output.getText().toString().equals("0");
    }

    private void clearCurrentOperationLabel() {
        this.currentOperationText.setText("");
    }

    private void setCurrentOperationLabel() {
        this.currentOperationText.setText(this.currentOperation.getLabel());
    }

    private void allClear() {
        this.output.setText("0");
        this.currentValue = 0;
        this.currentOperation = EArithmeticOperation.BLANK;
        this.isPerformingOperation = false;
        this.isCeActive = false;
        this.clearCurrentOperationLabel();
    }


    private void appendNumber(CharSequence number) {
        if(isOutputEmpty()) {
            this.output.setText(number);
        } else {
            if(this.isPerformingOperation) {
                this.output.setText(number);
                this.isPerformingOperation = false;
            } else {
                this.output.append(number);
            }
        }
    }

    public void setIntegerIfPossible() {
        double outputDoubleValue = Double.parseDouble(this.output.getText().toString());
        if(outputDoubleValue % 1 == 0) {
            this.output.setText(String.valueOf((int)outputDoubleValue));
        }
    }

}
