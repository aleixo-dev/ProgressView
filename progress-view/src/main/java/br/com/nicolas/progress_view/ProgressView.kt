package br.com.nicolas.progress_view

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.use
import br.com.nicolas.progress_view.databinding.ProgressViewRootBinding

class ProgressView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyle: Int = 0
) : ConstraintLayout(context, attributeSet, defStyle) {

    private val binding = ProgressViewRootBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    private var initialProgress: Int = 0
    private var maxProgress: Int = 0

    private var backgroundProgressView: Drawable? = null
    private var backgroundProgressViewEmpty: Drawable? = null

    private var backgroundContainerNumberView : Drawable? = null
    private var backgroundContainerFinalView : Drawable? = null

    init {
        context.obtainStyledAttributes(attributeSet, R.styleable.ProgressView).use { style ->
            initialProgress = style.getInt(R.styleable.ProgressView_initialProgress, 0)
            maxProgress = style.getInt(R.styleable.ProgressView_maxProgress, 0)
            backgroundProgressView =
                style.getDrawable(R.styleable.ProgressView_background_progress_view)
            backgroundProgressViewEmpty =
                style.getDrawable(R.styleable.ProgressView_background_progress_empty)
            backgroundContainerNumberView =
                style.getDrawable(R.styleable.ProgressView_background_container_number_view)
            backgroundContainerFinalView =
                style.getDrawable(R.styleable.ProgressView_background_container_final_view)
        }

        viewTreeObserver.addOnGlobalLayoutListener {
            with(binding) {
                setupLayoutProgressWidth()
                setupConstraintBias()
                setupViews()
            }
        }
    }

    private fun ProgressViewRootBinding.setupViews() {

        viewProgressEmpty.background =
            backgroundProgressViewEmpty ?: AppCompatResources.getDrawable(
                context,
                R.drawable.background_progress_empty
            )
        viewProgressFull.background = backgroundProgressView ?: AppCompatResources.getDrawable(
            context,
            R.drawable.background_progress_full
        )

        containerProgressNumber.background = backgroundContainerNumberView ?: AppCompatResources.getDrawable(
            context,
            R.drawable.background_circle_default
        )

        containerFinalView.background = backgroundContainerFinalView ?: AppCompatResources.getDrawable(
            context,
            R.drawable.background_circle_default
        )

    }

    private fun ProgressViewRootBinding.setupLayoutProgressWidth() {

        val greenViewLayoutParams = viewProgressFull.layoutParams as LayoutParams
        greenViewLayoutParams.matchConstraintMaxWidth = viewProgressEmpty.width

        val desireWidth = (viewProgressEmpty.width / maxProgress)
        if (initialProgress <= maxProgress) {
            greenViewLayoutParams.width = (desireWidth * initialProgress) - removeWidthCircleView()
            viewProgressFull.layoutParams = greenViewLayoutParams
            textViewCurrentNumber.text = initialProgress.toString()
        }
    }

    private fun removeWidthCircleView(): Int {
        val isEven = maxProgress % 2 == 0
        return when {
            initialProgress == 0 || initialProgress >= maxProgress -> 0
            !isEven -> binding.containerProgressNumber.width
            else -> 0
        }
    }

    private fun ProgressViewRootBinding.setupConstraintBias() {
        with(ConstraintSet()) {
            clone(containerRoot)
            val progressGreenWidth = getWidth(viewProgressFull.id)
            val bias = progressGreenWidth / viewProgressEmpty.width.toFloat()
            setHorizontalBias(containerProgressNumber.id, bias)
            applyTo(containerRoot)
        }
    }
}