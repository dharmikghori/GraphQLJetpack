package com.graphql.task.ui.composables


import android.content.Context
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.widget.doOnTextChanged
import com.graphql.task.theme.AppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditText(
    modifier: Modifier = Modifier,

    // Data
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    title: String = "",
    prefix: String = "",
    enabled: Boolean = true,
    error: Boolean = false,
    errorMessage: String = "",
    limit: Int = Int.MAX_VALUE,
    singleLine: Boolean = true,
    leadingIcon: @Composable () -> Unit = {},

    // Keyboard
    inputType: Int = InputType.TYPE_CLASS_TEXT,
    imeOptions: Int = EditorInfo.IME_ACTION_NEXT,
    imeAction: () -> Unit = {},

    // Customisation
    editTextFactory: EditText .() -> Unit = {},
    updateEditText: EditText.() -> Unit = {}
) {

    val interactionSource = remember { MutableInteractionSource() }

    Column(modifier) {
        AnimatedVisibility(visible = title.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(bottom = 6.dp),
                text = title,
                style = AppTheme.typography.regularMontserrat14,
                color = AppTheme.colors.black
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 5.dp)
                .clip(RoundedCornerShape(12.dp))
                .then(
                    if (error) {
                        Modifier.border(
                            color = AppTheme.colors.red,
                            width = 1.dp,
                            shape = RoundedCornerShape(12.dp)
                        )
                    } else {
                        Modifier
                    }
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (prefix.isNotEmpty()) {
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .offset(y = (1).dp),
                    text = prefix,
                    style = AppTheme.typography.regularMontserrat14,
                )
                Spacer(modifier = Modifier.width(4.dp))
            } else {
                Spacer(modifier = Modifier.width(12.dp))
            }

            val createEditText = { context: Context ->
                EditText(context).apply {

                    val params = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT
                    )
                    layoutParams = params

                    doOnTextChanged { s, _, _, _ ->
                        onValueChange(s.toString())
                    }

                    setPadding(0, 0, 0, 0)

                    background = null

                    textSize = 12.0f
                    gravity = Gravity.CENTER_VERTICAL
                    setInputType(inputType)
                    filters = arrayOf(InputFilter.LengthFilter(limit))

                    setImeOptions(imeOptions)
                    setOnEditorActionListener { _, actionId, _ ->
                        if (actionId == EditorInfo.IME_ACTION_DONE) {
                            imeAction.invoke()
                            false
                        } else true
                    }
                }.apply(editTextFactory)
            }

            val update: (EditText) -> Unit = {
                if (it.text.toString() != value) {
                    it.setText(value)
                }

                updateEditText(it)
            }


            Box(
                modifier = Modifier
                    .weight(1f)
                    .offset(y = (1).dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        modifier = Modifier.fillMaxWidth(),
                        style = AppTheme.typography.regularMontserrat14,
                        color = if (error) AppTheme.colors.red.copy(alpha = 0.3f) else AppTheme.colors.black
                    )
                }

                TextFieldDefaults.TextFieldDecorationBox(
                    value = value,
                    contentPadding = PaddingValues(0.dp),
                    visualTransformation = VisualTransformation.None,
                    innerTextField = {
                        AndroidView(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 10.dp),
                            factory = createEditText,
                            update = update,
                        )
                    },
                    singleLine = singleLine,
                    enabled = enabled,
                    isError = error,
                    interactionSource = interactionSource,
                )
            }

            leadingIcon()
        }
        AnimatedVisibility(visible = errorMessage.isNotEmpty()) {
            Text(
                modifier = Modifier.padding(top = 6.dp),
                text = errorMessage,
                style = AppTheme.typography.mediumMontserrat14,
                color = AppTheme.colors.red
            )
        }
    }
}

