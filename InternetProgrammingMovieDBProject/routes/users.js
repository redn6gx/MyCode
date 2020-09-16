var express = require('express');
var router = express.Router();
var mysql = require("mysql");

/* GET users listing. */
router.get('/', function(req, res, next) {
  // res.send('respond with a resource');
  
  if(req.session && req.session.username && req.session.username.length){
    res.render('user', {logged: req.session.username, loggedIn: true});
    return;
  }
  
  res.render('user', {layout: false});
});

router.post('/new', function (req, res, next) {
  console.log(req.body);
  let availability = false
  let username = req.body.username;
  const connection = mysql.createConnection({
    host: 'if0ck476y7axojpg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com',
    user: 'ek6fr7bzgpv230y8',
    password: 'acdcp33j961libcw',
    database: 'c5qwwjxdop5awyw3'
  });
  connection.connect();
  connection.query(`
SELECT username
FROM user_table
WHERE username = "${username}" 
`, function (error, results, fields) {
    console.log(results);
    if (error) throw error;
    //console.log('The quotes are: ', results);
    if (results.length != 0){
      res.json({
        success: false,
        message: "Username already in use"
      });
    }else{
      console.log("here");
      availability = true;
      let result = addUser(availability,req.body.username, req.body.fullname, req.body.password);
      res.json({
          success: true,
          message: "Account successfully made"
        })
    }
  });
  connection.end();
  console.log("over");
});
router.post('/review', function (req,res,next) {
  //Do a mysql call to database to add reviews
  //return success or failure
});
router.post('/movie',function (req,res,next) {
  //add movie to database if already not in database
  //use this link if a ajax call is made in adding movie gets a result in the movie page
});
router.post('/getuser',function (req,res,next) {
console.log(req.body);
});
let addUser = function(e,user,name,pass){
    if (e) {
        const nconnection = mysql.createConnection({
            host: 'if0ck476y7axojpg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com',
            user: 'ek6fr7bzgpv230y8',
            password: 'acdcp33j961libcw',
            database: 'c5qwwjxdop5awyw3'
        });
        nconnection.connect();
        nconnection.query(`
INSERT INTO user_table(username,fullname,password) VALUES(?, ?, ?)
`, [user, name, pass], (error, results, fields) => {
            console.log(results);
            if (error) throw error;
        });
        nconnection.end();
    }
};


///////////////////////////////////////////////////////////////////////////////////////////////////

// SELECT *
// FROM review_table
// WHERE userid = "${pageOwnerId}"

// SELECT *
// FROM review_table
// LEFT JOIN movie_table
// ON review_table.movieid = movie_table.movieid;

router.post("/getReviews",function (req,res,next) {
    console.log(req.body);
    let pageOwnerId = req.session.userid;
  
    const dconnection = mysql.createConnection({
        host: 'if0ck476y7axojpg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com',
        user: 'ek6fr7bzgpv230y8',
        password: 'acdcp33j961libcw',
        database: 'c5qwwjxdop5awyw3'
    });
    dconnection.connect();
    
    dconnection.query(`
SELECT *
FROM review_table
LEFT JOIN movie_table
ON review_table.movieid = movie_table.movieid
WHERE userid = "${pageOwnerId}";
`, function(error, results, fields){
        console.log("here are the reviews: ", results);
        console.log(error);
        if (error) throw error;
            res.json({
                response: "Successfully retrieved reviews",
                retrievedReviews: results
            });
    });
    dconnection.end();
});

router.post("/deleteReview",function (req,res,next) {
    console.log(req.body);
    let reviewid = req.body.reviewid
    console.log("js id", reviewid);
    const dconnection = mysql.createConnection({
        host: 'if0ck476y7axojpg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com',
        user: 'ek6fr7bzgpv230y8',
        password: 'acdcp33j961libcw',
        database: 'c5qwwjxdop5awyw3'
    });
    dconnection.connect();
    dconnection.query(`
DELETE
FROM review_table
WHERE reviewid = "${reviewid}"
`, function(error, results, fields){
        console.log("here are the reviews: ", results);
        console.log(error);
        if (error) throw error;
            res.json({
                 response: "Successfully deleted review"
            });
    });
    
    dconnection.end();
});

router.post("/updateReview",function (req,res,next) {
    console.log(req.body);
    let reviewid = req.body.reviewid
    let newReview = req.body.review;
    let newDate =  new Date();
    let newScore = req.body.score
    
    const dconnection = mysql.createConnection({
        host: 'if0ck476y7axojpg.cbetxkdyhwsb.us-east-1.rds.amazonaws.com',
        user: 'ek6fr7bzgpv230y8',
        password: 'acdcp33j961libcw',
        database: 'c5qwwjxdop5awyw3'
    });
    dconnection.connect();
    dconnection.query(`
UPDATE review_table
SET review = "${newReview}", score = "${newScore}"
WHERE reviewid = "${reviewid}"
`, function(error, results, fields){
        console.log("here are the reviews: ", results);
        console.log(error);
        if (error) throw error;
            res.json({
                response: "Successfully updated review"
            });
    });
});

module.exports = router;
