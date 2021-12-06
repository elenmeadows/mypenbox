
/* GLOBAL SCOPE = just a var statement
var a = 10;

LOCAL SCOPE = var in function's curly brackets
function axample () {
var b = 20;
}

FUNCTION SCOPE = smth that is in curly brackets of function. This var b below can be accessed only from inside the function.
function axample () {
var b = 20;
}

BLOCK SCOPE = curly brackets there
it is the BLOCK scope when it's used in Conditions (examples below) or Loops. Compared to FUNCTION SCOPE var a can be accessed from outside the given condition. But that property of BLOCK SCOPE (that access can mess up the code..)
if(1 == 1) {
var a = 10;
}

OR

else {

}

LET & CONST FIX THE FLAW OF VAR:
if (1 == 1) {
let b = 10;
}
console.log(b);
BUT IT GIVES ERROR MESSAGE.
So, in other words, LET fixes the risk that VAR causes in BLOCK SCOPE (accessibility).

CONST compared to LET cannot be changed later on.
const EXAMPLE = 10;

HOISTING = when we run the code inside the browser and we parse it, then it's going to read all the JS code and all the declarations (var a, var b, var c. JUST the fact that they EXIST) are going to be taken out of code and put at the top of the document.
But their VALUE ("First", "Second". "Third") won't be taken out to the top!!!

var a = "First";
var b = "Second";
var c = "Third";

AS FOR THE NAMED FUNCTIONS AND ANONYMOUS FUNCTIONS:
NAMED FUNCTIONS ARE BEING HOISTED THE SAME WAY VAR's ARE, WHICH MEANS WE CAN USE IT WHEREVER WE WANT INSIDE THE DOCUMENT.
BUT HOISTING IS GONNA SLOW DOWN THE CODE WHEN IT GETS LOADED IN BROWSER. SO, THERE IS NO NEED TO HOIST EVERYTHING UNLESS A SPECIFIC REASON. Solution is to create variables INSIDE the functions and not in an empty stroke of cdocument.
But how to avoid functions from being hoisted, this is where anonymous functions come into place.
var b = function() {
var asmr = 20;
return asmr;
}

console.log (b());

*/
