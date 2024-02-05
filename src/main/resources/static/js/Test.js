function doTheTest() {

    let uaCapital = parseInt(prompt("What is the Capital of Ukraine?\n" +
        "1. Moscow\n" +
        "2. Kiev\n" +
        "3. Astana"));
    let result = 0;
    let firstAnswer;
    if (uaCapital === 2) {
        result++;
        firstAnswer = "Correct!";
    } else {
        firstAnswer = "Wrong! The correct answer is 2, your is " + uaCapital;
    }
    let plus = parseInt(prompt("2 + 5?"));
    let secondAnswer;
    if (plus === 7) {
        result++;
        secondAnswer = "Correct!";
    } else {
        secondAnswer = "Wrong! The correct answer is 7, your is " + plus;
    }

    let chemistry = parseInt(prompt("The chemistry form of 'Water'?\n" +
        "1. CH2OH\n" +
        "2. O2\n" +
        "3. H2O"));
    let thirdAnswer;
    if (chemistry === 3) {
        result++;
        thirdAnswer = "Correct!";
    } else {
        thirdAnswer = "Wrong! The correct answer is 3, your is " + chemistry;
    }

    let multiply = parseInt(prompt("5 * 5?"));
    let fourthAnswer;
    if (multiply === 25) {
        result++;
        fourthAnswer = "Correct!";
    } else {
        fourthAnswer = "Wrong! The correct answer is 25, your is " + multiply;
    }

    let ukCapital = parseInt(prompt("The capital of UK?\n" +
        "1. London\n" +
        "2. Kiev\n" +
        "3. Paris"));
    let fifthAnswer;
    if (ukCapital === 1) {
        result++;
        fifthAnswer = "Correct!";
    } else {
        fifthAnswer = "Wrong! The correct answer is 3, your is " + ukCapital;
    }

    if (result === 5) {
        alert("Amazing!!!")
    } else if (result === 4) {
        alert("Good!")
    } else if (result === 3) {
        alert("Not bad!")
    } else if (result === 1 || result === 2) {
        alert("You can do it better!")
    } else if (result === 0) {
        alert("Very bad, try again")
    }


    let userResult = document.getElementById("result");
    let a1 = document.getElementById("a1");
    let a2 = document.getElementById("a2");
    let a3 = document.getElementById("a3");
    let a4 = document.getElementById("a4");
    let a5 = document.getElementById("a5");
    let percent = document.getElementById("rate");

    userResult.textContent = "Count of correct answers: " + result;

    a1.textContent = firstAnswer;
    a2.textContent = secondAnswer;
    a3.textContent = thirdAnswer;
    a4.textContent = fourthAnswer;
    a5.textContent = fifthAnswer;
    let percentA = result / 5 * 100;
    percent.textContent = "Rate: " + percentA + "%";
}