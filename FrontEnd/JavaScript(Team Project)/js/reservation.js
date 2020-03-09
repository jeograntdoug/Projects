{
	//Input for other pet able/disable
	$("#otherPetRadio").on('focusin', function () {
		$("#otherPet")
			.attr('disabled', false);

		//set value to #otherPet in sebmittion
	});

	$("input[type=radio]").not("#otherPetRadio").on('focus', function () {
		$("#otherPet").attr('disabled', true);
		$("#otherPet").val("");
		$("#otherPetRadio").val("other");
	});


	// Reset form
	$("#reset").click(function () {
		$("input[type=text]").val("");
		$("textarea").val("");
		$("input[value=dog]").prop("checked", true);
		$("#otherPet").prop("disabled", true);
		$("select").children("option:first-child").prop("selected", true);
		$("#resvForm").removeClass("was-validated");

		$("option#docDefaultOption").siblings().remove();
	});


	//Set other pet value when submit button click
	$("button[type=submit]").mouseup(function () {
		if( $("#otherPetRadio").prop('checked')){
			if($("#otherPet").val() === ""){
				$("#otherPet").val("other" );
			}
		}
	});


	// validate input
	// Send(submit) form
	$("#resvForm").submit(function (event) {
		let form = $(this)[0];
		if (form.checkValidity() === false) {
			event.preventDefault();
			event.stopPropagation();
			
			//Initialize pet input box
			if( $("#otherPetRadio").prop('checked')){
				$("#otherPet").val("" );
			}
		} else {
		}
		form.classList.add("was-validated");
	});

	/** End of Form Javascript **/




	/** Reservation Scadule table Javascript **/


	//Test Data
	let dummies =[];

	{
		let td = new Date();
		let doc = ["Bill","Devon"];
		let count = 80;

		for(let i = 0; i < count; i++){
			let ranDay = Math.floor(Math.random() * 8);
			let ranHours= Math.floor(Math.random() * 10) + 8;

			let year = td.getFullYear();
			let month = td.getMonth();
			let day = td.getDate() + ranDay;

			dummies.push({
				time:new Date(year, month, day, ranHours),
				doctor:doc[i%2]
			});
		}
	}
	/*
	let sDummies = [
		{ time: new Date(2019,11,18,12), doctor: "Bill" },
		{ time: new Date(2019,11,20,14), doctor: "Devon" },
		{ time: new Date(2019,11,21,9), doctor: "Bill" },
		{ time: new Date(2019,11,20,13), doctor: "Devon" },
		{ time: new Date(2019,11,17,8), doctor: "Bill" },
		{ time: new Date(2019,11,19,13), doctor: "Devon" },
		{ time: new Date(2019,11,19,17), doctor: "Bill" },
		{ time: new Date(2019,11,23,9), doctor: "Devon" },
		{ time: new Date(2019,11,23,12), doctor: "Devon" },
		{ time: new Date(2019,11,22,9), doctor: "Bill" },
		{ time: new Date(2019,11,20,13), doctor: "Bill" },
		//
		{ time: new Date(2019,11,18,12), doctor: "Devon" },
		{ time: new Date(2019,11,20,14), doctor: "Bill" },
		{ time: new Date(2019,11,21,9), doctor: "Devon" },
		{ time: new Date(2019,11,20,13), doctor: "Bill" },
		{ time: new Date(2019,11,17,8), doctor: "Devon" },
		{ time: new Date(2019,11,19,13), doctor: "Bill" },
		{ time: new Date(2019,11,19,17), doctor: "Devon" },
		{ time: new Date(2019,11,23,9), doctor: "Bill" },
		{ time: new Date(2019,11,23,12), doctor: "Bill" },
		{ time: new Date(2019,11,22,9), doctor: "Devon" },
		{ time: new Date(2019,11,20,13), doctor: "Devon" },
		//
		{ time: new Date(2019,11,19,13), doctor: "Devon" },
		{ time: new Date(2019,11,21,15), doctor: "Bill" },
		{ time: new Date(2019,11,22,10), doctor: "Devon" },
		{ time: new Date(2019,11,21,12), doctor: "Bill" },
		{ time: new Date(2019,11,18,7), doctor: "Devon" },
		{ time: new Date(2019,11,20,12), doctor: "Bill" },
		{ time: new Date(2019,11,20,16), doctor: "Devon" },
		{ time: new Date(2019,11,24,8), doctor: "Bill" },
		{ time: new Date(2019,11,24,11), doctor: "Bill" },
		{ time: new Date(2019,11,23,10), doctor: "Devon" },
		{ time: new Date(2019,11,21,12), doctor: "Devon" },
		//
		{ time: new Date(2019,11,18,8), doctor: "Bill" },
		{ time: new Date(2019,11,19,10), doctor: "Devon" },
		{ time: new Date(2019,11,19,11), doctor: "Bill" },
		{ time: new Date(2019,11,20,11), doctor: "Devon" },
		{ time: new Date(2019,11,20,9), doctor: "Bill" },
		{ time: new Date(2019,11,20,8), doctor: "Devon" },
		{ time: new Date(2019,11,20,16), doctor: "Bill" },
		{ time: new Date(2019,11,18,11), doctor: "Devon" },
		{ time: new Date(2019,11,18,15), doctor: "Devon" },
		{ time: new Date(2019,11,18,16), doctor: "Bill" },
		{ time: new Date(2019,11,18,17), doctor: "Bill" },
		//
		{ time: new Date(2019,11,19,13), doctor: "Bill" },
		{ time: new Date(2019,11,21,15), doctor: "Devon" },
		{ time: new Date(2019,11,22,10), doctor: "Bill" },
		{ time: new Date(2019,11,21,12), doctor: "Devon" },
		{ time: new Date(2019,11,18,7), doctor: "Bill" },
		{ time: new Date(2019,11,20,12), doctor: "Devon" },
		{ time: new Date(2019,11,20,16), doctor: "Bill" },
		{ time: new Date(2019,11,24,8), doctor: "Devon" },
		{ time: new Date(2019,11,24,11), doctor: "Devon" },
		{ time: new Date(2019,11,23,10), doctor: "Bill" },
		{ time: new Date(2019,11,21,12), doctor: "Bill" },
	];
	*/

	const dayOfWeek = ["Sun","Mon","Tue","Wed","Thu","Fri","Sat"];

	let schedule = dummies;// Suppose to get from server(JSON)
	let doctor1 = "Bill";
	let doctor2 = "Devon";

	let dayArray = [[],[],[],[],[],[],[]];

	// Make empty Array for schedule table 10(Hours) X 7(day)
	let scheduleArray = [];
	for(let hours=0; hours< 10; hours++){
		scheduleArray.push([[],[],[],[],[],[],[]]);
	}


	/*
	* Fill scheduleArray[hours][day]={doc1,doc2}
	* from Array schedule(data from server) 
	*/
	makeScheduleArray();
	printOnTable();
	


	// Generate option elements of date selection 
	$("#date").append(function(){

		let html = "";
		let today = new Date();

		// Display today scadule on schedule table,
		// But not on date select (cannot reserve today schedule)
		// start day = 1
		for(let day=1; day < scheduleArray[0].length; day++){
			let dayFromToday= (day + today.getDay()) % 7;

			for(let hours=0; hours < scheduleArray.length; hours++)
			{
				if( (scheduleArray[hours][dayFromToday].doc1=== undefined)
					|| (scheduleArray[hours][dayFromToday].doc2 === undefined))
				{
					html += `<option value="h${hours}d${dayFromToday}">`
						+`${dayOfWeek[dayFromToday]} at ${hours+8}:00`
						+`</option>`;
				}
			}
		}
		return html;
	});


	// When user select date, show only avaliable doctor
	$("select[name=date]").on('input',function() {
		let idStr = $(this).children(":selected").val();

		//id format : h{hours}d{day}
		let data = idStr.split(/[hd]/);

		preSelectDoctor(data[1],data[2]);

	});


	//When you click the cell of schedule table
	// action: if there is empty spot 
	// 			- move screen/select options/drow outline

	$("td").click(function () {

		// rocation in table
		let col = $(this).prop("cellIndex");
		let row = $(this).parent().prop("rowIndex");

		// you can't make reservation today (col = 1 is today)
		if(col === 1){
			//TODO: change to grayout message
			alert("You can't make reservation today");
			return;
		}

		// today is always first col in the table
		let day = (new Date().getDay() + col - 1) % 7;
		let hours = row - 1;

		// If there is no spot => return;
		if ( (scheduleArray[hours][day].doc1 !== undefined
				&& scheduleArray[hours][day].doc2 !== undefined) ) 
		{
			$(this).css('outline', '1px solid red');

			$("#defaultDate").prop('selected',true);

			return;	// Nothing to do
		}


		// Preselect date select option
		// in value, h:hours/d:day => to parse in the server
		$("select#date")
			.children(`option[value=h${hours}d${day}]`)
			.prop("selected", true);

		// Move screen to form
		$(window).scrollTop($("form").offset().top - 100);

		preSelectDoctor(hours,day);

	});


	function preSelectDoctor(hours,day){

		// Remove all doctors from selection
		$("option#docDefaultOption").siblings().remove();

		let docName = [];

		// get doctor who has a schedule 
		if (scheduleArray[hours][day].doc1) {
			docName.push(doctor1);
		}
		if (scheduleArray[hours][day].doc2) {
			docName.push(doctor2);
		}


		// Show only avaliable doctor
		let html = "";

		switch(docName.length) {
			case 0://everybody avaliable
				html = `<option id=${doctor1}>Dr. ${doctor1}</option>`
					+ `<option id=${doctor2}>Dr. ${doctor2}</option>`;
				break;
			case 1:
				for (let name of docName) {
					let availableDoc = name == doctor1 ? doctor2 : doctor1;
					html = `<option id=${availableDoc}>Dr. ${availableDoc}</option>`;
				}
				break;
			default:
				console.log("It should not happen");
				return;
		}

		// Add avaliable doctor(s) in the selection
		$("select#doctor").append(html);
		
		
		// Point out the date selected
		$("select#date")
			.css('outline','3px solid blue')
			.animate({
				outlineWidth: 0
			},2000);

		$("select#doctor")
			.css('outline','3px solid blue')
			.animate({
				outlineWidth: 0
			},2000);

		// Preselect avaliable option
		$("#docDefaultOption").next().prop('selected','true');

		// Remove validation marks
		$("#resvForm").removeClass("was-validated");

	}




	function makeScheduleArray(){

		let tableText = "<tr>";

		let scheduleSize = schedule.length;
		if(scheduleSize === 0 ){
			return;
		}

		//TEMP: Sort (must be done in server side)
		schedule.sort(function(a,b) {
			return a.time - b.time;
		});

		// make another array for display only
		let today= new Date();


		// Display all today scadule(setHours(0)) on schedule table,
		// But not on date select (cannot reserve today schedule)
		today.setHours(0);

		let aWeekAfter = new Date(today.getFullYear()
								,today.getMonth()
								,today.getDate() + 7);

		for (let i = 0; i < scheduleSize; i++) {
			let resvDate = schedule[i].time;

			// period of schedule in table: from today to a week later
			if(resvDate < today){
				continue;
			} 
			if(resvDate > aWeekAfter){
				break;
			}

			let resvDay = resvDate.getDay() ;

			//Hours:0 = 8:00/ Hours:9 = 17:00
			let resvHours = resvDate.getHours() - 8;

			if(resvHours > 9 || resvHours < 0){
				console.log(`Wrong Reservation info - day: ${resvDay}, hours: ${resvHours}`);
				continue;
			}

			switch(schedule[i].doctor){
				case doctor1:
					scheduleArray[resvHours][resvDay]['doc1'] = true;
					break;
				case doctor2:
					scheduleArray[resvHours][resvDay]['doc2'] = true;
					break;
				default:
					console.log("System Error!: should not happen");
					return;
			}
		}
	}

	//display schedule on the table
	function printOnTable() {
		let dayOfToday = new Date().getDay();

		//Print header
		let header = "<tr><th>Hours</th>";

		for (let day = 0; day < dayOfWeek.length; day++) {
			header += "<th>" + dayOfWeek[(day + dayOfToday) % 7] + "</th>";
		}
		header += "</th>";
		$("thead").append(header);


		//Print Body
		for (let hours = 0; hours < scheduleArray.length; hours++) {
			let body = "<tr><th>" + (hours + 8) + ":00</th>";

			for (let day = 0; day < scheduleArray[hours].length; day++) {
				let dayFromToday = (day + dayOfToday) % 7;

				let doc1 = "";
				let doc2 = "";

				if (scheduleArray[hours][dayFromToday].doc1) {
					doc1 = '<p class="bg-secondary">' + doctor1 + "</p>";
				}

				if (scheduleArray[hours][dayFromToday].doc2) {
					doc2 = '<p class="bg-primary">' + doctor2 + "</p>";
				}

				body += "<td>" + doc1 + doc2 + "</td>";

			}

			body += "</tr>";
			$("tbody").append(body);
		}
	}

}
/** End of Reservation Scadule table Javascript **/

//TODO: get available Schedule from server
//TODO: Display User's new Schedule
//	- Get response from server(200) 
//		=> add user's personal array
//		=> display
//TODO: Html element name has to be specific(avoid override)
//TODO: variables has to be local
//	- Wrap with {} ???
//TODO: server send new data when someone reserve spot
//	- Pusher? | ask server every sec (ajax)
//TODO: Check duplicate reservation
//TODO: change to grayout message
