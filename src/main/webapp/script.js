// For main page
function loadHabits() {
    fetch('/display-habit').then(response => response.json()).then((habitJson) => {
      const habitListElement = document.getElementById('habit-name');
      habitJson.forEach((habitJson) => {
        habitListElement.appendChild(loadHabitElement(habitJson));
        // TODO: Implement Tick functionality to mark habit as completed
        // habitListElement.appendChild(createHabitTick(habitName)); 
      })
      console.log("Fetch habits");
      console.log(habitJson);
    });
  }

// For main page
function loadHabitElement(habitJson) {
  // Convert Habit JSON Object to String (without the single quote)
  var habitNameString = JSON.parse(JSON.stringify(habitJson.habitName));
  
  const done = habitJson.isComplete ? "done" : "";
  // Create <li> elements for each habit
  const habitElement = document.createElement('li');
  //habitElement.className = 'habit';
  //habitElement.innerHTML = habitJson.habitName;
  habitElement.setAttribute("class", `habit-item ${done}`);
  habitElement.setAttribute("data-key", habitJson.id);

  // Create <span> elements for each habit
  const titleElement = document.createElement('span');
  titleElement.innerText = habitJson.habitName;

  // Create checkbox for each habit
  const checkbox = document.createElement("input");
  checkbox.id = habitJson.id;
  checkbox.type = "checkbox";
  const tick = document.createElement("label");
  tick.setAttribute("for", `${habitJson.id}`);
  tick.className = "tick js-tick";

  tick.addEventListener("click", () => {
      if(!done)
      {
          toggleDone(habitJson);
          console.log("ticking...");
          location.reload();
      }

  })

  habitElement.appendChild(titleElement);
  habitElement.appendChild(checkbox);
  habitElement.appendChild(tick);
  return habitElement;
}


// For Modify Page
function reloadHabits() {
  fetch('/display-habit').then(response => response.json()).then((habitJson) => {
    const habitListElement = document.getElementById('create-habit-name');
    habitJson.forEach((habitJson) => {
      habitListElement.appendChild(createHabitElement(habitJson));
      // TODO: Implement Tick functionality to mark habit as completed
      // habitListElement.appendChild(createHabitTick(habitName)); 
    })
    console.log("Fetch habits");
    console.log(habitJson);
  });
}

// For Modify Page
function createHabitElement(habitJson) {
  // Create <li> elements for each habit
  const habitElement = document.createElement('li');
  habitElement.setAttribute("class", `habit-item`);

  // Create <span> elements for each habit
  const titleElement = document.createElement('span');
  titleElement.innerText = habitJson.habitName;

  // Create delete <button> elements for each habit 
  const deleteButtonElement = document.createElement('button');
  deleteButtonElement.className = "button";  
  deleteButtonElement.innerText = 'X';
  deleteButtonElement.addEventListener('click', () => {
    deleteHabit(habitJson);
    // Convert Habit JSON Object to String (without the single quote)
    console.log(JSON.parse(JSON.stringify(habitJson.habitName)));
    loadHabits();
    //habitElement.remove();
  })

  habitElement.appendChild(titleElement);
  habitElement.appendChild(deleteButtonElement);
  return habitElement;
}

function deleteHabit(habit) {
  const params = new URLSearchParams();
  params.append('id', habit.id);
  console.log(params.id);
  fetch('/delete-habit', {method: 'POST', body: params});
}
  
function deleteHabit(habit) {
  const params = new URLSearchParams();
  params.append('id', habit.id);
  console.log(params.id);
  fetch('/delete-habit', {method: 'POST', body: params});
}
  
  // Toggle done icon of a habit based on key value
function toggleDone(habit) {
  const params = new URLSearchParams();
  params.append('id', habit.id);
  console.log("calling update servlet");
  fetch('/update-habit', {method: 'POST', body: params});
}

/*
let habitItems = [];
// Create a new habit item based on the text from input
// Add to habitItems list
function addHabit(text) {
  const habit = {
    text,
    checked: false,
    id: Date.now(),
  };
  habitItems.push(habit);
  renderHabit(habit);
}
// Select the from element
form = document.querySelector("#js-form");
// Submit event listener
// When a new habit sumbitted, it will be added to habitItems list
form.addEventListener("submit", (event) => {
  event.preventDefault();
  console.log("Received submit event from form...");
  //prevent page from refreshing when form submitted
  event.preventDefault();
  // Select text input
  input = document.querySelector("#js-habit-input");
  // Process input
  // Remove whitespace
  text = input.value.trim();
  // Add to habitItems list
  if (text !== "") {
    addHabit(text);
    console.log("add habit");
    input.value = "";
    input.focus();
  }
});
function renderHabit(habit) {
  // Select list item in html
  const list = document.querySelector("#js-habit-list");
  // Check if habit is in DOM already
  const item = document.querySelector(`[data-key = '${habit.id}']`);
  // If delete is called
  // Remove item
  if (habit.deleted) {
    item.remove();
    return;
  }
  // See if habit is checked
  const isChecked = habit.checked ? "done" : "";
  // Create an `li` element and assign it to `node`
  const node = document.createElement("li");
  // Set the class attribute
  node.setAttribute("class", `habit-item ${isChecked}`);
  node.setAttribute("data-key", habit.id);
  // Set the contents of the li element
  const checkbox = document.createElement("input");
  checkbox.id = habit.id;
  checkbox.type = "checkbox";
  const tick = document.createElement("label");
  tick.setAttribute("for", `${habit.id}`);
  tick.className = "tick js-tick";
  const text = document.createElement("span");
  text.innerText = habit.text;
  const del = document.createElement("button");
  del.className = "delete-habit js-delete-habit";
  del.innerHTML = "X";
  node.append(checkbox, tick, text, del);
  // If item already in DOM
  if (item) {
    list.replaceChild(node, item);
  } else {
    list.append(node);
  }
}
// Mark a habit as done
const list = document.querySelector("#js-habit-list");
list.addEventListener("click", (event) => {
  // Toggle done for the habit with checkbox checked
  if (event.target.classList.contains("js-tick")) {
    const itemKey = event.target.parentElement.dataset.key;
    console.log("ticking...");
    toggleDone(itemKey);
  }
  // Detele items
  if (event.target.classList.contains("delete-habit")) {
    const itemKey = event.target.parentElement.dataset.key;
    console.log("deleting...");
    deleteHabit(itemKey);
  }
});
// Toggle done icon of a habit based on key value
function toggleDone(key) {
  // Find index of this habit based on key
  const index = habitItems.findIndex((item) => item.id === Number(key));
  // False to true, true to false
  habitItems[index].checked = !habitItems[index].checked;
  renderHabit(habitItems[index]);
}
function deleteHabit(key) {
  // Find index of this habit based on key
  const index = habitItems.findIndex((item) => item.id === Number(key));
  // Create a new object with same property to current to item but with deleted as true
  const habit = {
    deleted: true,
    ...habitItems[index],
  };
  // Delete the original item by filtering it out
  habitItems = habitItems.filter((item) => item.id !== Number(key));
  renderHabit(habit);
}
*/