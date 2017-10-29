var promise = require('bluebird');
var bodyParser = require('body-parser');
var options = {
  // Initialization Options
  promiseLib: promise
};

var pgp = require('pg-promise')(options);
var connectionString = 'postgres://postgres:s123456@localhost:5432/ulpa';
var db = pgp(connectionString);

// add query functions

module.exports = {
  getAllData: getAllData,
  getSingleLanguage: getSingleLanguage,
  getLocation: getLocation,
  getAllStates:getAllStates,
  getUniversityInState:getUniversityInState,
  getStateUniversityLanguage:getStateUniversityLanguage,
  getAllStateData:getAllStateData,
  getLanguageList:getLanguageList,
  getAllLanguageDetail:getAllLanguageDetail,
  storemessage:storemessage

};


function storemessage(req, res, next){
	 var receivemessage = req.params.message;
	
            console.log(receivemessage);
        
}

function getAllLanguageDetail(req, res, next) {
  db.any('select languages_language.name , languages_language.description  from public.languages_language')
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved ALL states'
        });
    })
    .catch(function (err) {
      return next(err);
    });

    
}

function getAllStateData(req, res, next) {
  var scState = req.params.statename;
  db.any('select subjects_subject.code,subjects_subject.title,universities_university.logo, subjects_subject.url, subjects_subject.state ,subjects_subject.other_university, subjects_subject.intensity ,subjects_subject.prerequisite ,subjects_subject.non_beginner_level_available ,subjects_subject.next_offered ,subjects_subject.study_choice, languages_language.name as language_name ,universities_university.name as university_name from subjects_subject INNER JOIN universities_university on subjects_subject.university_id= universities_university.id inner join languages_language on subjects_subject.language_id=languages_language.id where state = $1',scState)
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved ALL Data'
        });
    })
    .catch(function (err) {
      return next(err);
    });

    
}

function getAllData(req, res, next) {
  db.any('select * from public.testdb')
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved ALL Data'
        });
    })
    .catch(function (err) {
      return next(err);
    });

    
}

function getAllStates(req, res, next) {
  db.any('select distinct subjects_subject.state from public.subjects_subject')
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved ALL states'
        });
    })
    .catch(function (err) {
      return next(err);
    });

    
}






function getLanguageList(req, res, next) {
    db.any('select distinct languages_language.name from subjects_subject inner join languages_language on subjects_subject.language_id=languages_language.id  ')
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved list of all language'
        });
    })
    .catch(function (err) {
      return next(err);
    });

}



function getSingleLanguage(req, res, next) {
  var scLanguage = req.params.language_name;
    db.any('select  subjects_subject.code,subjects_subject.title,universities_university.logo, subjects_subject.url, subjects_subject.state ,subjects_subject.other_university, subjects_subject.intensity ,subjects_subject.prerequisite ,subjects_subject.non_beginner_level_available ,subjects_subject.next_offered ,subjects_subject.study_choice, languages_language.name as language_name ,universities_university.name as university_name  from subjects_subject INNER JOIN universities_university on subjects_subject.university_id= universities_university.id inner join languages_language on subjects_subject.language_id=languages_language.id where languages_language.name = $1', scLanguage)
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved Data of one language'
        });
    })
    .catch(function (err) {
      return next(err);
    });
}











function getLocation(req, res, next) {
  var state = req.params.state;
    db.any('select * from testdb where state = $1', state)
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved Data of one state'
        });
    })
    .catch(function (err) {
      return next(err);
    });
  
}

function getUniversityInState(req, res, next) {
  var state = req.params.state;
    db.any('select distinct universities_university.name from subjects_subject inner join universities_university on subjects_subject.university_id=universities_university.id where state = $1', state)
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved University of one state'
        });
    })
    .catch(function (err) {
      return next(err);
    });

}



function getStateUniversityLanguage(req, res, next) {
  var scState = req.params.findingstate;
  var scUniversity=req.params.findinguniversity
    db.any('select language_name from testdb where state = $1 and university = $2', scState,scUniversity)
    .then(function (data) {
      res.status(200)
        .json({
          status: 'success',
          data: data,
          message: 'Retrieved Data of one language'
        });
    })
    .catch(function (err) {
      return next(err);
    });
}



