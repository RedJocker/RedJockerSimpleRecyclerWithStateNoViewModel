package com.example.testrecyclerwithstate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), MyListAdapterService{

    private val listView: RecyclerView by lazy {
        findViewById(R.id.listView)
    }
    private val button: Button by lazy {
        findViewById(R.id.button)
    }

    private val listModelItems = (0..20).map {
        ModelWithState(it, "item $it", false)
    }

    private val myListAdapter = MyListAdapter(listModelItems, this)

    private var currentModel: ModelWithState? = null
        set(value) {
            field = value
            button.text = "ToggleIsChecked ${value?.text}"
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView.adapter = myListAdapter

        button.setOnClickListener {
            println("button click")
            val model = currentModel
            if(model != null) {
                model.isSelected = model.isSelected.not()
                println("currentModel $model")
                myListAdapter.notifyItemChanged(model.index)
            }
        }

    }

    override fun setCurrentItem(modelItem: ModelWithState) {
        currentModel = modelItem
    }

    override fun getCurrentItem(): ModelWithState? {
        return currentModel
    }
}