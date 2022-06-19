package com.olvera.stores_mvvm.mainModule

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.olvera.stores_mvvm.*
import com.olvera.stores_mvvm.databinding.ActivityMainBinding
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityMainBinding

    private lateinit var mAdapter: StoreAdapter
    private lateinit var mGridLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       /* binding.btnSave.setOnClickListener {
            val store = StoreEntity(name = binding.etName.text.toString().trim())

            Thread {
                StoreApplication.database.storeDao().addStore(store)
            }.start()

            mAdapter.add(store)
        }*/

        binding.fab.setOnClickListener {
            launchEditFragment()
        }

        setupRecyclerView()

    }

    private fun launchEditFragment() {
        val fragment = EditStoreFragment()

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.add(R.id.containerMain, fragment)
        fragmentTransaction.commit()
        fragmentTransaction.addToBackStack(null)
        binding.fab.hide()
    }

    private fun setupRecyclerView() {
        mAdapter = StoreAdapter(mutableListOf(), this)
        mGridLayout = GridLayoutManager(this, 2)
        getStores()

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = mGridLayout
            adapter = mAdapter
        }
    }

    private fun getStores() {
        doAsync {
            val stores = StoreApplication.database.storeDao().getAllStores()
            uiThread {
                mAdapter.setStores(stores)
            }
        }
    }

    /**
     * OnClicklistener
     */
    override fun onClick(store: StoreEntity) {
        TODO("Not yet implemented")
    }

    override fun onFavoriteStore(store: StoreEntity) {
        store.isFavorite = !store.isFavorite
        doAsync {
            StoreApplication.database.storeDao().updateStore(store)
            uiThread {
                mAdapter.update(store)
            }
        }
    }

    override fun onDeleteStore(store: StoreEntity) {
        doAsync {
            StoreApplication.database.storeDao().deleteStore(store)
            uiThread {
                mAdapter.delete(store)
            }
        }
    }
}