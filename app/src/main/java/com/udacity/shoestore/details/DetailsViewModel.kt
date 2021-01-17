package com.udacity.shoestore.details

import androidx.lifecycle.*
import com.udacity.shoestore.models.Shoe

class DetailsViewModel : ViewModel() {

    //Mutable live data bound to the layout
    var shoeName = MutableLiveData<String>()
    var shoeSize = MutableLiveData<String>()
    var company = MutableLiveData<String>()
    var description = MutableLiveData<String>()

    //Booleans to check that info has been filled
    var shoeNameEntered = false
    var shoeSizeEntered = false
    var companyEntered = false
    var descriptionEntered = false

    //Private mutable live data to check if all the information has been filled.
    //Uses MediatorLiveData to observe the multiple LiveData EditText objects
    private fun _canSaveShoe(): MutableLiveData<Boolean> {
        val result = MediatorLiveData<Boolean>()
        result.addSource(shoeName) {
            if(fieldComplete(it)){
                shoeNameEntered = true
            }
            result.value = infoComplete()
        }
        result.addSource(shoeSize){
            if (fieldComplete(it)){
                shoeSizeEntered = true
            }
            result.value = infoComplete()
        }
        result.addSource(company){
            if (fieldComplete(it)){
                companyEntered = true
            }
            result.value = infoComplete()
        }
        result.addSource(description){
            if (fieldComplete(it)){
                descriptionEntered = true
            }
            result.value = infoComplete()
        }
        return result
    }

    //Public observable Live Data to enable the save menu.
    val canSaveShoe: LiveData<Boolean>
        get() = _canSaveShoe()


    init {
        shoeName.value = ""
        shoeSize.value = ""
        company.value = ""
        description.value = ""
    }

    //Checks if the EditText field has been filled
    private fun fieldComplete(string: String): Boolean {
        return string.isNotEmpty()
    }

    //Checks if all the EditText fields have been filled
    private fun infoComplete(): Boolean {
        return shoeNameEntered && shoeSizeEntered && companyEntered && descriptionEntered
    }

    //Returns a Shoe
    fun getShoe(): Shoe{
        return Shoe(shoeName.value.toString(), shoeSize.value.toString().toDouble(), company.value.toString(), description.value.toString(), emptyList())
    }

}