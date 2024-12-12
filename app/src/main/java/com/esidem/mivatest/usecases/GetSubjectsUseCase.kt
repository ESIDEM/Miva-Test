package com.esidem.mivatest.usecases

import android.content.res.Resources
import com.esidem.mivatest.R
import com.esidem.mivatest.models.Subject
import javax.inject.Inject

class GetSubjectsUseCase @Inject constructor(
    private val resources: Resources
) {
    fun getSubjects(): List<Subject> = listOf(
        Subject(
            title = resources.getString(R.string.mathematics),
            icon = R.drawable.math
        ),
        Subject(
            title = resources.getString(R.string.english),
            icon = R.drawable.english
        ),
        Subject(
            title = resources.getString(R.string.biology),
            icon = R.drawable.biology
        ),
        Subject(
            title = resources.getString(R.string.chemistry),
            icon = R.drawable.chemistry
        ),
        Subject(
            title = resources.getString(R.string.physics),
            icon = R.drawable.physics
        ),
        Subject(
            title = resources.getString(R.string.government),
            icon = R.drawable.government
        ),
        Subject(
            title = resources.getString(R.string.accounting),
            icon = R.drawable.accounting
        ),
        Subject(
            title = resources.getString(R.string.economics),
            icon = R.drawable.economics
        ),
        Subject(
            title = resources.getString(R.string.literature),
            icon = R.drawable.literature
        )
    )
}
