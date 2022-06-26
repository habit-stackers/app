
//habit list item array
let habitItems = [];

//create a new habit item based on the text from input
//add to habitItems list
function addHabit(text) {
    const habit = {
      text,
      checked: false,
      id: Date.now(),
    };
  
    habitItems.push(habit);
    renderHabit(habit);
    console.log(habitItems);
  }

//select the from element 
form = document.querySelector("#js-form");
if(form)
{
    console.log("get form");

//submit event listener
//when a new habit sumbitted, it will be added to habitItems list
    form.addEventListener('submit', event => {
        event.preventDefault();
        console.log("submitted");
        //prevent page from refreshing when form submitted
        event.preventDefault();
    
        //select text input
        input = document.querySelector('#js-habit-input');
    
        //process input
        //remove whitespace
        text = input.value.trim();
        //add to habitItems list
        if (text !== ''){
            addHabit(text);
            console.log("add habit");
            input.value = '';
            input.focus();
        }
    });
}
//render habits on the screen
function renderHabit(habit){
    //select list item in html
    const list = document.querySelector('#js-habit-list');
    //check if habit is in DOM already 
    const item = document.querySelector(`[data-key = '${habit.id}']`);

    //if delete is called
    //remove item
    if(habit.deleted){
        item.remove();
        return;
    }


    //see if habit is checked
    const isChecked = habit.checked ? 'done':'';
    //create an `li` element and assign it to `node`
    const node = document.createElement("li");
    //set the class attribute
    node.setAttribute('class', `habit-item ${isChecked}`);
    node.setAttribute('data-key',habit.id);
    //set the contents of the li element
    node.innerHTML = `
        <input id="${habit.id}" type = "checkbox"/>
        <label for="${habit.id}" class = "tick js-tick"></label>
        <span>${habit.text}</span>
        <button class = "delete-habit js-delete-habit">
            <svg><use href="#delete-icon"></use></svg>
        </button>
    `;

    //if item already in DOM
    if(item){
        list.replaceChild(node,item);
    }
    else{
    //append the li elemnt to DOM
    //as last child of element referenced by `list` variable
    list.append(node);
    }

}

//mark a habit as done
const list = document.querySelector('#js-habit-list');
if(list)
{
    list.addEventListener('click', event => {
        //toggle done
        if(event.target.classList.contains('js-tick')){
            const itemKey = event.target.parentElement.dataset.key;
            console.log("ticking...");
            toggleDone(itemKey);
        }

        //detele items
        if(event.target.classList.contains('delete-habit')){
            const itemKey = event.target.parentElement.dataset.key;
            console.log("deleting...");
            deleteHabit(itemKey);
        }
    });


}


//toggle done icon of a habit based on key value
function toggleDone(key){
    //find index of this habit based on key
    const index = habitItems.findIndex(item => item.id === Number(key));
    //false to true, true to false
    habitItems[index].checked = !habitItems[index].checked;
    renderHabit(habitItems[index]);
}


function deleteHabit(key){
        //find index of this habit based on key
        const index = habitItems.findIndex(item => item.id === Number(key));

        //create a new object with same property to current to item but with deleted as true
        const habit = {
            deleted:true,
            ...habitItems[index]
        };
        //delete the original item by filtering it out
        habitItems = habitItems.filter(item => item.id !== Number(key));
        renderHabit(habit);
}

