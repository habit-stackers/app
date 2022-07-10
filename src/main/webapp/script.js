

// This is like my main function?
function loadHabits() {
  fetch('/list-habit').then(response => response.json()).then((habit) => {
    const habitListElement = document.getElementById('list-name');
    habit.forEach((habitName) => {
      habitListElement.appendChild(createHabitElement(habitName));
    })
    console.log("Fetch habits");
    console.log(habit);
  });
}

function createHabitElement(habitName) {
  const habitElement = document.createElement('li');
  habitElement.className = 'habit';
  habitElement.innerHTML = habitName;

  //const titleElement = document.createElement('span');
  //titleElement.innerText = habit.title;

  return habitElement;
}

/*
form = document.querySelector("#js-form").addEventListener("submit", (event) =>
{
  event.preventDefault();

});
*/

/*let habitItems = [];

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