package your.package.name //Replace with the app's package name (like: com.example.mycalculator)

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private var currentInput: String = ""
    private var operand1: Double? = null
    private var currentOperation: Char? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)


        val buttons = mapOf(
            R.id.button0 to "0",
            R.id.button1 to "1",
            R.id.button2 to "2",
            R.id.button3 to "3",
            R.id.button4 to "4",
            R.id.button5 to "5",
            R.id.button6 to "6",
            R.id.button7 to "7",
            R.id.button8 to "8",
            R.id.button9 to "9",
            R.id.buttonDecimal to "."
        )


        for((buttonId,buttonText) in buttons) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener {onNumberInput(buttonText)}

        }

        findViewById<Button>(R.id.buttonClear).setOnClickListener { onClearClick() }
        findViewById<Button>(R.id.buttonChangeSign).setOnClickListener { onChangeSignClick() }
        findViewById<Button>(R.id.buttonPercent).setOnClickListener { onPercentClick() }

        findViewById<Button>(R.id.buttonPlus).setOnClickListener { onOperationClick('+') }
        findViewById<Button>(R.id.buttonMinus).setOnClickListener { onOperationClick('-') }
        findViewById<Button>(R.id.buttonMultiply).setOnClickListener { onOperationClick('*') }
        findViewById<Button>(R.id.buttonDivide).setOnClickListener { onOperationClick('/') }


        findViewById<Button>(R.id.buttonEquals).setOnClickListener { onEqualClick() }


    }
    private fun onNumberInput(digit: String) {
        currentInput += digit
        updateDisplay()
    }

    private fun onOperationClick(operation: Char) {

        if(currentInput.isNotEmpty()) {

            if (operand1 == null) {
                operand1 = currentInput.toDouble()
            } else {

                onEqualClick();

                operand1 = currentInput.toDouble();


            }
            currentInput=""
            currentOperation = operation

        }
    }
    private fun onClearClick() {
        currentInput = ""
        operand1 = null
        currentOperation = null
        updateDisplay()
    }
    private fun onChangeSignClick() {
        if(currentInput.isNotEmpty()){
            currentInput = (-1 * currentInput.toDouble()).toString()
            updateDisplay()
        }
    }
    private fun onPercentClick() {
        if(currentInput.isNotEmpty()) {
            currentInput = (currentInput.toDouble() / 100).toString();
            updateDisplay();
        }
    }

    private fun onEqualClick() {

        if (operand1 != null && currentOperation != null && currentInput.isNotEmpty()) {
            val operand2 = currentInput.toDouble();

            var result= when (currentOperation) {
                '+' -> operand1!! + operand2
                '-' -> operand1!! - operand2
                '*' -> operand1!! * operand2
                '/' -> {
                    if(operand2 != 0.0) operand1!! / operand2
                    else null
                }

                else -> null


            }


            currentInput = if(result!=null) result.toString()
            else "Error"


            operand1=null
            currentOperation = null
            updateDisplay()
        }

    }


    private fun updateDisplay() {
        resultTextView.text = currentInput
    }
}