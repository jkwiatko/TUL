package com.j4c08.calcu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Advanced extends AppCompatActivity{

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
        setContentView(R.layout.activity_advanced);
        ButterKnife.bind(this);

        if(savedInstanceState != null) {
            this.output.setText(savedInstanceState.getString("output"));
            this.currentOperation = (EArithmeticOperation)savedInstanceState.getSerializable("currentOperation");
            this.currentValue = savedInstanceState.getDouble("currentValue");
            this.isPerformingOperation = savedInstanceState.getBoolean("isPerformingOperation");
            this.isCeActive = savedInstanceState.getBoolean("isCeActive");
        } else {
            this.output.setText("0");
        }
    }

    @OnClick(R.id.zero)
    public void onClickZero(Button zero) {
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

    @OnClick(R.id.plusMinus)
    public void onClickPlusMinus(Button plusMinus) {
        if(!output.getText().toString().equals("0")) {
            double outputNumber = Double.parseDouble(this.output.getText().toString());
            this.output.setText(String.valueOf(-outputNumber));
            setIntegerIfPossible();
        }
    }

    @OnClick(R.id.dot)
    public void onClickDot(Button dot) {
        if(!this.output.getText().toString().contains(".")) {
            this.output.append(".");
        }
    }

    @OnClick(R.id.ce)
    public void onClickCe(Button cce) {
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
    public void onClickPlus(Button add) {
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
    public void onClickMultiply(Button multiply) {
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

    @OnClick(R.id.sqrt)
    public void onClickSqrt(Button sqrt) {
        if(Double.parseDouble(this.output.getText().toString()) >= 0) {
            this.output.setText(String.valueOf(Math.sqrt(Double.parseDouble(this.output.getText().toString()))));
            setIntegerIfPossible();
        } else {
            Toast.makeText(this, "You can't sqrt by less than 0", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.square)
    public void onClickSquare(Button square) {
        this.output.setText(String.valueOf(Math.pow(Double.parseDouble(this.output.getText().toString()), 2)));
        setIntegerIfPossible();
    }

    @OnClick(R.id.pow)
    public void onClickPower(Button pow) {
        this.isCeActive = false;
        this.currentOperation = EArithmeticOperation.POWER;
        setCurrentOperationLabel();
        if(!isPerformingOperation && currentValue != 0) {
            double oldValue = currentValue;
            currentValue = Math.pow(currentValue, Double.parseDouble(output.getText().toString()));

            output.setText(String.valueOf(Math.pow(oldValue, Double.parseDouble(this.output.getText().toString()))));
            setIntegerIfPossible();

            this.isPerformingOperation = true;
        } else {
            isPerformingOperation = true;
            this.currentValue = Double.parseDouble(this.output.getText().toString());
        }
    }

    @OnClick(R.id.sin)
    public void onClickSin(Button sin) {
        this.output.setText(String.valueOf(Math.sin(Double.parseDouble(this.output.getText().toString()))));
        setIntegerIfPossible();
    }

    @OnClick(R.id.cos)
    public void onClickCos(Button cos) {
        this.output.setText(String.valueOf(Math.cos(Double.parseDouble(this.output.getText().toString()))));
        setIntegerIfPossible();
    }

    @OnClick(R.id.tan)
    public void onClickTan(Button tan) {
        this.output.setText(String.valueOf(Math.tan(Double.parseDouble(this.output.getText().toString()))));
        setIntegerIfPossible();
    }

    @OnClick(R.id.logn)
    public void onClickLn(Button ln) {
        this.output.setText(String.valueOf(Math.log(Double.parseDouble(this.output.getText().toString()))));
        setIntegerIfPossible();
    }

    @OnClick(R.id.log)
    public void onClickLog(Button log) {
        this.output.setText(String.valueOf(Math.log10(Double.parseDouble(this.output.getText().toString()))));
        setIntegerIfPossible();
    }

    @OnClick(R.id.perc)
    public void onClickPerc(Button percent) {
        this.isCeActive = false;
        this.currentOperation = EArithmeticOperation.PERCENT;
        setCurrentOperationLabel();
        if(!isPerformingOperation && currentValue != 0) {
            double oldValue = currentValue;
            currentValue = Double.parseDouble(output.getText().toString());

            output.setText(String.valueOf(oldValue / 100 * Double.parseDouble(this.output.getText().toString())));
            setIntegerIfPossible();

            this.isPerformingOperation = true;
        } else {
            isPerformingOperation = true;
            this.currentValue = Double.parseDouble(this.output.getText().toString());
        }
    }

    @OnClick(R.id.count)
    public void onClickCount(Button count) {
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
                }
                case POWER: {
                    if(!isPerformingOperation) {
                        this.output.setText(String.valueOf(Math.pow(this.currentValue,
                                Double.parseDouble(this.output.getText().toString()))));
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
                } case PERCENT: {
                    if (!isPerformingOperation) {
                        this.output.setText(String.valueOf(this.currentValue / 100
                                * Double.parseDouble(this.output.getText().toString())));
                        setIntegerIfPossible();

                        this.isPerformingOperation = true;
                        this.currentOperation = EArithmeticOperation.BLANK;
                        this.currentValue = 0;
                        this.clearCurrentOperationLabel();
                    }
                    break;
                } default: {
                    break;
                }
            }
        }
    }

    private boolean isOutputEmpty() {
        return output.getText().toString().equals("0");
    }

    private void allClear() {
        this.output.setText("0");
        this.currentValue = 0;
        this.currentOperation = EArithmeticOperation.BLANK;
        this.isPerformingOperation = false;
        this.isCeActive = false;
        this.clearCurrentOperationLabel();
    }

    public void setIntegerIfPossible() {
        double outputDoubleValue = Double.parseDouble(this.output.getText().toString());
        if(outputDoubleValue % 1 == 0) {
            this.output.setText(String.valueOf((int)outputDoubleValue));
        }
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

    private void setCurrentOperationLabel() {
        this.currentOperationText.setText(this.currentOperation.getLabel());
    }

    private void clearCurrentOperationLabel() {
        this.currentOperationText.setText("");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("output", this.output.getText().toString());
        outState.putSerializable("currentOperation", this.currentOperation);
        outState.putDouble("currentValue", this.currentValue);
        outState.putBoolean("isPerformingOperation", this.isPerformingOperation);
        outState.putBoolean("isCeActive", this.isCeActive);

        super.onSaveInstanceState(outState);
    }
}

