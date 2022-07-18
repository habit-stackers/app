// For main page
function loadHabits() {
    // Display title
    fetch('/display-habit').then(response => response.json()).then((habitJson) => {
      const habitListElement = document.getElementById('habit-name');
      habitJson.forEach((habitJson) => {
        habitListElement.appendChild(loadHabitElement(habitJson));
      })
      console.log("Fetch habits");
      console.log(habitJson);
    });
    // Display list
    fetch('/display-list').then(response => response.json()).then((listJson) => {
        listJson.forEach((listJson) => {
            console.log("loading title...");
            const listNameElement = document.getElementById('app-title');
            console.log(listNameElement);
            const titleElement = document.createElement('h1');
            titleElement.innerText = listJson.listName;
            listNameElement.append(titleElement);
        })
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
    toggleDone(habitJson);
    console.log("ticking...");
    location.reload();

  })

  habitElement.appendChild(titleElement);
  habitElement.appendChild(checkbox);
  habitElement.appendChild(tick);
  return habitElement;
}


// For Modify Page
function reloadHabits() {
    //Display list
  fetch('/display-habit').then(response => response.json()).then((habitJson) => {
    const habitListElement = document.getElementById('create-habit-name');
    habitJson.forEach((habitJson) => {
      habitListElement.appendChild(createHabitElement(habitJson));
    })
    console.log("Fetch habits");
    console.log(habitJson);
  });
  // Display title
  fetch('/display-list').then(response => response.json()).then((listJson) => {
    listJson.forEach((listJson) => {
        console.log("loading title...");
        const listNameElement = document.getElementById('app-title');
        console.log(listNameElement);
        const titleElement = document.createElement('h1');
        titleElement.innerText = listJson.listName;
        listNameElement.appendChild(titleElement);
    })
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
      console.log(habitJson.habitName);
      loadHabits();
      location.reload();
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

