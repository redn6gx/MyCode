var express = require('express');
var router = express.Router();
const mysql = require('mysql');
const session = require('express-session');

router.use(session({
    secret: '6wOBwJBStY'
}));

router.get('/', function(req, res, next) {
    res.render('login', { layout: false });
});

router.get('/home', function(req, res, next) {
    if (req.session && req.session.username && req.session.password && req.session.password === "password123") {
        res.render('index', { layout: false });
    }
    else {
        delete req.session.password;
        delete req.session.username;
        res.redirect('/routes/');
    }
});

router.post('/', function(req, res, next) {
    console.log("test");
    var success = false;
    var errorMessage = "";
    var username = req.body.username;
    var password = req.body.password;
    const connection = mysql.createConnection({
        host: 'if0ck476y7axojpg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com',
        user: 'ek6fr7bzgpv230y8',
        password: 'acdcp33j961libcw',
        database: 'c5qwwjxdop5awyw3'
    });
    connection.connect();
    connection.query(`
SELECT *
FROM user_table
WHERE username = "${username}"
`, function(error, results, fields){
        console.log(results);
        if (error) throw error;
        if (results.length === 0){
            delete req.session.username;
            console.log("username");
            res.json({
               error: "Incorrect username and/or password!"
            });
        }else if (results[0].password !== password){
            delete req.session.username;
            console.log("password");
            res.json({
                error: "Incorrect username and/or password!"
            });
        }else {
            req.session.username = username;
            req.session.userid = results[0].userid;
            console.log("success");
            console.log(req.session.username);
            console.log(req.session.userid);
            res.json({
                success: true
            })
        }


    });
    connection.end();

});

router.get('/signOut', function(req, res, next) {
    var message = "";
    if (req.session && req.session.username) {
        delete req.session.username;
        delete req.session.id;
        if (req.session.password) {
            delete req.session.password;
        }
    }
    else {
        message = "Error, logout has failed! Please try again.";
    }

    res.json({
        successful: true,
        message: message
    });

});

module.exports = router;