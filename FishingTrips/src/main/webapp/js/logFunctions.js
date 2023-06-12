window.addEventListener('load', function(e){
	console.log('LOADED')
	init();
});

function init(){
	getAllLogs();
	
	/*document.getElementById('idLink').addEventListener('click', function(event){
		event.preventDefault();
		let logId = document.getElementById('idLink').innerHTML;
		console.log(logId)
		if (!isNaN(logId) && logId > 0){
			getLog(logId);
		}
	})*/
	
	
	document.newLogForm.createLogButton.addEventListener('click', function(event){
		event.preventDefault();
		let logValues = document.newLogForm;
		let logObject = {
			fish : logValues.fish.value,
			water : logValues.water.value,
			weight : logValues.weight.value,
			length : logValues.length.value,
			time : logValues.time.value,
			date : logValues.date.value
		};
		console.log(logObject)
		createLog(logObject);
	})
	
}

function getAllLogs(){
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/logs');
	xhr.onreadystatechange = function(){
		if (xhr.readyState === 4){
			if (xhr.status === 200){
				let logs = JSON.parse(xhr.responseText);
				displayAllLogs(logs);
			} else {
				displayErrors('Error fetching all logs');
			}
		}
	};
	xhr.send();
}

function displayAllLogs(logs){
	let logDataDiv = document.getElementById('logData');
		logDataDiv.textContent = '';
	if (logs && Array.isArray(logs) && logs.length > 0){
		let logTable = document.createElement('table');
			logTable.id = 'logTable';
			logDataDiv.appendChild(logTable);
		let logHead = document.createElement('thead');
			logTable.appendChild(logHead);
		let logBody = document.createElement('tbody');
			logTable.appendChild(logBody);
		
		let logHeadTr = document.createElement('tr');
			logHead.appendChild(logHeadTr);
		let idHeader = document.createElement('th');
			idHeader.textContent = 'Log';
			logHeadTr.appendChild(idHeader);
		let fishHeader = document.createElement('th');
			fishHeader.textContent = 'Fish Species';
			logHeadTr.appendChild(fishHeader);
		let waterHeader = document.createElement('th');
			waterHeader.textContent = 'Body Of Water';
			logHeadTr.appendChild(waterHeader);
		let weightHeader = document.createElement('th');
			weightHeader.textContent = 'Weight (lbs)';
			logHeadTr.appendChild(weightHeader);
		let lengthHeader = document.createElement('th');
			lengthHeader.textContent = 'Length (in)';
			logHeadTr.appendChild(lengthHeader);
		let timeHeader = document.createElement('th');
			timeHeader.textContent = 'Time of Day';
			logHeadTr.appendChild(timeHeader);
		let dateHeader = document.createElement('th');
			dateHeader.textContent = 'Date';
			logHeadTr.appendChild(dateHeader);
		
		for (let log of logs){
			let logBodyTr = document.createElement('tr');
				logBody.appendChild(logBodyTr);
			let idData = document.createElement('td');
				idData.className = 'clickable';
				let idLink = document.createElement('a');
					idLink.href = log.id;
					idLink.innerText = log.id;
					idData.appendChild(idLink);
				logBodyTr.appendChild(idData);
			let fishData = document.createElement('td');
				fishData.textContent = log.fish.commonName;
				logBodyTr.appendChild(fishData);
			let waterData = document.createElement('td');
				waterData.textContent = log.water.name;
				logBodyTr.appendChild(waterData);
			let weightData = document.createElement('td');
				weightData.textContent = log.weight;
				logBodyTr.appendChild(weightData);
			let lengthData = document.createElement('td');
				lengthData.textContent = log.length;
				logBodyTr.appendChild(lengthData);
			let timeData = document.createElement('td');
				timeData.textContent = log.time.timeframe;
				logBodyTr.appendChild(timeData);
			let dateData = document.createElement('td');
				dateData.textContent = log.date;
				logBodyTr.appendChild(dateData);
		}
		
	logTable.addEventListener('click', function(event) {
		event.preventDefault();
		console.log(event.target.innerText, 'was clicked');
		let logId = event.target.innerText;
		console.log(logId)
		if (!isNaN(logId) && logId > 0){
			getLog(logId);
		}
	});
		
	}
}

function getLog(logId){
	let xhr = new XMLHttpRequest();
	xhr.open('GET', 'api/logs/' + logId);
	xhr.onreadystatechange = function(){
		if (xhr.readyState === 4){
			if (xhr.status === 200){
				let log = JSON.parse(xhr.responseText);
				displayLog(log);
			} else {
				displayErrors('Log not found');
			}
		}
	};
	xhr.send();
}

function displayLog(log){
	let logDataDiv = document.getElementById('logData');
		logDataDiv.textContent = '';
	let logHeader = document.createElement('h1');
		logHeader.textContent = 'Catch Log #' + log.id;
		logHeader.style.textDecorationLine = 'underline';
		logDataDiv.appendChild(logHeader);
	let fish = document.createElement('h2');
		fish.textContent = 'Type of fish caught : ' + log.fish.commonName;
		logDataDiv.appendChild(fish);
	let fishImage = document.createElement('img');
		fishImage.src = log.fish.image;
		fishImage.alt = 'Photo of ' + log.fish.commonName;
		fishImage.style.height = '100px';
		logDataDiv.appendChild(fishImage);
	let water = document.createElement('h2');
		water.textContent = 'From : ' + log.water.name;
		logDataDiv.appendChild(water);
	let waterImage = document.createElement('img');
		waterImage.src = log.water.image;
		waterImage.alt = 'Photo of ' + log.water.name;
		waterImage.style.height = '200px';
		logDataDiv.appendChild(waterImage);
	let weightLength = document.createElement('h3');
		weightLength.textContent = log.weight + ' pounds : ' + log.length + ' inches';
		logDataDiv.appendChild(weightLength);
	let dateTime = document.createElement('h2');
		dateTime.textContent = log.time.timeframe + ' of ' + log.date;
		logDataDiv.appendChild(dateTime);
		
	let deleteForm = document.createElement('form');
		deleteForm.name = 'deleteFilmForm';
		logDataDiv.appendChild(deleteForm);
	let logIdInput = document.createElement('input');
		logIdInput.type = 'hidden';
		logIdInput.name = 'logId';
		logIdInput.value = log.id;
		deleteForm.appendChild(logIdInput);
	let deleteButton = document.createElement('button');
		deleteButton.textContent = 'Delete This Log';
		deleteForm.appendChild(deleteButton);
		deleteButton.classList.add('btn');
		deleteButton.classList.add('btn-danger');
		
	deleteButton.addEventListener('click', function(event){
		event.preventDefault();
		let logId = document.deleteLogForm.logId.value;
		console.log('Delete log ' + logId)
		deleteLog(logId);
	});
		
}

function createLog(logObject){
	let xhr = new XMLHttpRequest();
	xhr.open('POST', 'api/logs');
	xhr.setRequestHeader('Content-type', 'application/json');
	xhr.onreadystatechange = function(){
		if (xhr.readyState === 4){
			if (xhr.status === 200 || xhr.status === 201){
				let log = JSON.parse(xhr.responseText);
				displayLog(log);
			} else {
				displayErrors('Error creating Catch Log, review your form input');
			}
		}
	};
	let logJson = JSON.stringify(logObject);
	xhr.send(logJson);
}

function deleteLog(logId){
	let xhr = new XMLHttpRequest();
	xhr.open('DELETE', 'api/logs/' + logId);
	xhr.onreadystatechange = function(){
		if (xhr.readyState === 4){
			if (xhr.status === 200 || xhr.status === 204){
				displayErrors('');
			} else {
				displayErrors('Deletion of Catch Log was unsuccessful')
			}
		}
	};
	xhr.send(null);
}

function displayErrors(errorMessage){
	let logDataDiv = document.getElementById('logData');
		logDataDiv.textContent = '';
	let fishDataDiv = document.getElementById('fishData');
		fishDataDiv.textContent = '';
	let lakeDataDiv = document.getElementById('lakeData');
		lakeDataDiv.textContent = '';
	let messageElement = document.createElement('h3');
		messageElement.textContent = errorMessage;
		messageElement.style.backgroundColor = '#E16666';
		logDataDiv.appendChild(messageElement);
}