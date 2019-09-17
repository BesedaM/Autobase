
function requiredInCheckbox() {

    var checkBoxList=document.getElementsByClassName('cars');
    for (var i=0;i<checkBoxList.length;i++){
        if(checkBoxList.item(i).checked){
            return true;
        }
    }
    return false;
}