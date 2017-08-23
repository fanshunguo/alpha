function persion(firstname, lastname, age, eyecolor) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.age = age;
    this.eyecolor = eyecolor;
    this.changeName = changeName;
    function changeName(name) {
        this.lastname = name;
    }
}

function Opts() {
    this.pw = 10;
    this.ph = 10;
    this.color = "DarkRed";
    this.point = [100, 100];
}