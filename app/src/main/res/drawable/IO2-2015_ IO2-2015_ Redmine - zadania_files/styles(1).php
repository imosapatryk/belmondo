/**************************************
 * THEME NAME: orangewhite
 *
 * Files included in this sheet:
 *
 *   orangewhite/styles_layout.css
 *   orangewhite/styles_fonts.css
 *   orangewhite/styles_color.css
 *   orangewhite/styles_tabs.css
 **************************************/

/***** orangewhite/styles_layout.css start *****/

/*******************************************************************
 styles_layout.css
  
 This CSS file contains all layout definitions like positioning,
 floats, margins, padding, borders etc.

 Styles are organised into the following sections:
  core
  header
  footer

  admin
  blocks
  calendar
  course
  doc
  login
  message
  user

  various modules

*******************************************************************/


/***
 *** Core
 ***/

body {
  margin:0.5em;
  padding:0
}
h1.main,
h2.main,
h3.main,
h4.main,
h5.main,
h6.main {
  margin-left:1em;
  text-align:left
}
#content {
  clear:both
}
.generalboxcontent {
  text-align:left
}
#layout-table {
  margin-top:0
}
#layout-table #left-column {
  width:22%;
  padding:5px
}
#layout-table #middle-column {
  width:auto;
  padding:5px
}
#layout-table #middle-column .topics {
  width:95%
}
#layout-table #right-column {
  width:22%;
  padding:5px
}
td#middle-column tr.section td.content,
td#middle-column tr.section td.side {
  border-style: none;
}
.generalbox {
  width:90%
}
.generaltable th.header {
  border-left:1px solid;
  border-right:1px solid;
}
.generaltable .smallinfo p {
  margin-top:0
}
.generaltable .cell {
  border-style: none;
}
.sitetopiccontent {
  border:0 none
}
img.resize {
  width: 1em;
  height: 1em;
}

/***
 *** Header
 ***/

#header-home {
  padding:0.7em 0;
  height:2em
}
#header,
#header-main,
.navbar {
  margin:0
}
#header {
  padding:0.1em 0;
  height:50px
}
#header .headermain {
  float:left;
  margin:0.2em 0 0 12px
}
.headermenu {
  float:right;
  text-align:right
}
.headermenu .logininfo {
  margin:0 12px 4px 0
}
.navbar {
  width:100%;
  margin:0;
  padding:0;
  background:url(pix/colorstrip.gif) left no-repeat;
  height:3em;
  border:0 none
}
.navbar .breadcrumb {
  float:left;
  margin:23px 0.2em 0 12px
}
.navbutton {
  float:right;
  padding-top:18px;
  margin:0.2em 1em 0.2em 0
}
.navbar hr {
  height:1px
}

/***
 *** Footer
 ***/
 
#footer {
  margin-top:1em
}
#footer br {
  display:none
}
#footer hr {
  display:none
}
#footer .sitelink {
  margin:0.5em 0
}
#footer .homelink {
  margin:0.5em
}
#footer .homelink a {
  border-width:1px
}

/***
 *** Admin
 ***/

#admin-index #content,
#admin-lang #content {
  padding-top: 10px;
  margin-top: 13px;
}
#admin-maintenance #content {
  padding-bottom: 15px;
}
#adminsettings fieldset {
  border-width: 1px;
  border-style: solid;
}
form.mform div.textarea textarea {
  width: 35em;
}

/***
 *** Content
 ***/

#content {
  clear:both;
  margin:0 12px
}
#course-view #content,
#site-index #content {
  margin:0
}
#user-edit .userpicture,
#user-view .userpicture {
  margin-top:10px
}

/***
 *** Admin
 ***/
 
.logtable th.header {
  border-left:1px solid;
  border-right:1px solid;
}
#user-edit #content .generalbox,
#user-view .userinfobox tr {
  border-top:0 none
}

/***
 *** Blocks
 ***/
 
.sideblock,
.sideblock .searchform {
  text-align:left
}
.sideblock .header .hide-show-image {
  margin-top:0.1em;
}
.sideblock .searchform a {
  line-height:1.5em
}
#left-column .sideblock {
  margin:0
}
#left-column .sideblock .header,
#left-column .sideblock .content,
#right-column .sideblock .header,
#right-column .sideblock .content {
  border:0 none
}
#right-column .sideblock .content {
  padding:0.2em 0.7em
}
#left-column .sideblock .header {
  border-bottom:1px solid
}
#left-column .sideblock .list .r0,
#left-column .sideblock .list .r1 {
  /* height:2em */
  min-height:2em
}
#left-column .sideblock .list td {
  vertical-align:middle;
  border-bottom:1px solid
}
#right-column .sideblock {
  margin-bottom:28px
}
.sideblock .head {
  margin-top:0.5em
}
.sideblock .link {
  margin:0.3em 0;
  border-bottom:1px solid
}
.sideblock .post {
  margin-top:0.5em;
  padding-bottom:0.2em;
  border-bottom:1px solid
}
.block_rss_client .link {
  border-top:0 none
}

/***
 *** Calendar
 ***/

#calendar .today,
.minicalendar .today {
  padding-top:0;
  padding-bottom:0;
  border:1px solid !important
}
#calendar .maincalendar .calendarmonth {
  width:75%
}
.sideblock .calendar-controls .current {
  text-align: center;
}

/***
 *** Course
 ***/

#site-index .headingblock {
  border:0 none
}
#course-view .headingblock {
  margin-left:1em;
  padding-left:0.7em;
  border:0 none;
  border-left:1.2em solid
}
#course-view .topics {
  margin:0 0.5em 0 1em
}
#course-view .section .left {
  width:0.8em
}
#course-view .section .right {
  width:30px
}
#course-view .section .left a {
  display:none
}
#course-view .section .spacer {
  height:2em
}
#course-view .section .activity .spacer {
  height:12px
}
#course-view .section .content .section {
  margin-top:0;
  margin-left:0.7em
}
#course-view .section .content .section .activity {
  padding:0.3em 0.2em 0.2em;
  border-bottom:1px solid
}
.course .section .content .summary {
  margin:0 0 0 0.7em
}
#course-view .section .content .section .label img {
  margin-right:1em;
  margin-bottom:1em
}
#course-user #content,
#mod-forum-user #content {
  padding-bottom:0.2em
}

/***
 *** Login
 ***/

.loginbox {
  margin-top:12px
}
  
/***
 *** Modules:Forum
 ***/
 
.forumheaderlist {
  width:90%;
  margin-left:auto;
  margin-right:auto
}
.forumheaderlist .header {
  border-left:1px solid;
  border-right:1px solid
}
#mod-forum-index .generalbox {
  width:100%
}
  
/***
 *** Modules:Glossary
 ***/

.glossarydisplay {
  border-bottom:5px solid
}
.glossarypopup {
  margin-top:0.5em
}

/***
 *** Modules:Resource
 ***/
 
.mod-resource #content {
  margin-top:1px
}

/***
 *** New stuff
 ***/

/***** orangewhite/styles_layout.css end *****/

/***** orangewhite/styles_fonts.css start *****/

/*******************************************************************
 styles_fonts.css
  
 This CSS file contains all font definitions like family, size,
 weight, text-align, letter-spacing etc.

 Styles are organised into the following sections:
  core
  header
  footer

  admin
  blocks
  calendar
  course
  doc
  help
  login
  message
  user

  various modules

*******************************************************************/


/***
 *** Core
 ***/

.clearer {
  font-size:1px
}
/** font {
  font-size:100%
}*/
body {
  font-family:Verdana, Arial, Helvetica, sans-serif;
  font-size:100%
}
th {
  font-weight:bold
}
a:link,
a:visited {
  text-decoration:none
}
a:hover {
  text-decoration:underline
}
a.autolink {
  font-size:100% !important;
}
a.autolink:hover {
  text-decoration:none;
  cursor:help
}
h1.main,
h2.main,
h3.main,
h4.main,
h5.main,
h6.main {
  font-weight:bold
}
h1 {
  font-size:1.2em
}
h2 {
  font-size:1em
}
h3 {
  font-size:0.8em
}
h4 {
  font-size:0.7em
}
pre code {
  font-size:1.2em
}
.bold {
  font-weight:bold
}
.breadcrumb .sep {
  font-family: verdana, arial, helvetica, sans-serif;
}
.generalboxcontent {
  font-size:0.8em
}
.generaltable .header {
  font-size:0.9em;
  font-weight:normal;
  white-space:normal !important
}
.generaltable .cell {
  font-size: 0.8em;
}
.warning {
  font-weight:bold;
  font-style:italic
}
.errorbox .title {
  font-weight:bold;
  font-size:1.2em;
  text-align:center
}
.pagingbar .title {
  font-weight:bold
}
.pagingbar .thispage {
  font-weight:bold
}
.paging {
  font-size:0.8em
}
.categorybox .category {
  font-size:1.2em;
  font-weight:bold
}
.helplink {
  font-size:0.8em
}
.headingblock {
  font-weight:bold
}
.headingblock .link {
  font-size:0.9em
}
.files .file {
  font-size:0.9em
}
.files .folder {
  font-size:0.9em
}
.files .folder .size {
  font-weight:bold
}
.sitetopiccontent {
  font-size:0.8em
}

/***
 *** Header
 ***/

.headermain {
  font-weight:bold
}
#header-home .headermain {
  font-size:1.5em
}
#header .headermain {
  font-size:1.3em
}
.breadcrumb {
  font-size:0.8em;
  font-weight:normal
}
.logininfo,
#header-home .headermenu font {
  font-size:0.8em
}

/***
 *** Footer
 ***/

.homelink {
  font-size:0.8em
}

.performanceinfo {
  font-size:0.6em
}

/***
 *** Admin
 ***/

table.formtable tbody th {
  font-weight:normal;
  text-align:right
}
#admin-index .generalboxcontent,
#admin-config .generalboxcontent {
  font-size:0.9em
}
#admin-config .linklist {
  font-size:0.9em
}
#admin-config .r1 {
  font-size:0.9em
}
#admin-config #content > center {
  font-size:0.8em
}
#admin-index .generaltable .cell {
  line-height: 1.9em;
}
#adminsettings fieldset span.form-shortname {
  font-size: 90%;
}
#adminsettings fieldset {
  font-size: 80%;
}

/***
 *** Blocks
 ***/
 
.sideblock {
  font-size:0.7em
}
.sideblock .header {
  font-weight:bold
}
.sideblock .content {
  line-height:1.2em
}
.sideblock .content *,
.sideblock .content .message {
  text-align:left
}
.sideblock .content h3,
.sideblock .content h2 {
  font-size:1.2em
}
.sideblock .header .commands {
}
.sideblock .footer {
  text-align:left
}
.sideblock .head,
.sideblock .info,
.sideblock .event {
}
.sideblock .date {
  font-style:italic
}
.activitydate, .activityhead {
  /*font-size:0.8em*/
}

/***
 *** Calendar
 ***/

#calendar .maincalendar .eventlist .event .referer {
  font-weight:bold
}
#calendar .maincalendar .eventlist .event .course {
  font-size:0.8em
}
#calendar .maincalendar .eventlist .event .description .commands {
  text-align:right
}
#calendar .maincalendar .calendarmonth {
  font-size:0.8em
}
#calendar .sidecalendar {
  font-size:0.8em
}
#calendar div.header {
  font-weight:bold
}
#calendar .sidecalendar .filters,
#calendar .maincalendar .filters {
  font-size:0.8em
}
.sideblock .filters td {
  font-size:1em
}
#calendar .maincalendar .controls {
  font-size:1em
}
#calendar .maincalendar .day {
  font-weight:bold
}
table.minicalendar {
  font-size:0.85em
}
.cal_popup_close {
  font-family:sans-serif;
  font-size:0.8em;
  font-weight:bold
}
.cal_popup_bg {
  font-size:1.2em
}
.cal_popup_cg {
  font-size:0.95em;
  font-weight:bold
}
#calendar .maincalendar .calendar-controls .current {
  font-weight:bold
}
.block_calendar_month .filters {
  font-size:0.9em
}
.block_calendar_month .day {
  text-align:center
}

/***
 *** Course
 ***/

#course-view .section {
  font-size:0.8em;
  line-height:1.4em
}
#course-view .section .label {
  font-size:1.2em;
  line-height:1.4em
}
#course-view .section .activity {
  padding:0.2em 0;
  vertical-align:bottom
}
#course-view .section .activity a {
  font-size:1.2em;
  line-height:1em
}
#course-view .section .left {
  font-weight:bold
}
.course .generalbox{
  font-size:0.8em
}
.weeklydatetext {
  font-size:0.8em;
  font-weight:bold
}
.coursebox .info {
  font-size:1em
}
.coursebox .teachers,
.coursebox .cost {
  font-size:0.8em
}
.coursebox .summary {
  font-size:0.8em
}
#course-recent h2.main {
  font-size:1.1em
}

/***
 *** Doc
 ***/

body#doc-contents h1 {
  font-size:0.9em
}
body#doc-contents ul {
  font-size:0.8em
}

/***
 *** Help
 ***/

#help {
  font-size:0.8em
}

/***
 *** Login
 ***/

#login-index #content .left,
#login-index #content .right {
  font-size:0.8em;
  text-align:left
}

/***
 *** Message
 ***/

.message .link {
  font-size:0.8em
}
.message_form {
  font-size:0.8em
}
.message .heading {
  font-size:1.0em;
  font-weight:bold
}
.message .date,
.message .contact,
.message .summary {
  font-size:0.9em
}
.message .note, 
.message .pix {
  font-size:0.8em
}
.message .author {
  font-weight:bold;
  font-size:0.8em
}
.message .time {
  font-style:italic;
  font-size:0.8em
}
.message .content {
  font-size:0.8em
}
#message-user .commands span {
  font-size:0.7em;
  white-space:nowrap
}
#message-user .name {
  font-weight:bold;
  font-size:1.1em
}

/***
 *** User
 ***/

#user-view .userinfobox .content {
  font-size:0.8em
}
#course-user .section .content td {
  font-size:0.8em
}
#course-user .section .content ul {
  font-size:1.2em
}
#course-user .logtable {
  font-size:0.8em
}
#course-log .logtable {
  font-size:0.8em
}
.userinfobox .username {
  font-weight:bold
}
.userinfobox .links {
  font-size:0.7em
}

/***
 *** Modules:Assignment
 ***/

#mod-assignment-index .cell {
  font-size:0.85em
}
/***
 *** Modules:Chat
 ***/

#mod-chat-index .cell {
  font-size:0.85em
}
/***
 *** Modules:Choice
 ***/

#mod-choice-index .cell {
  font-size:0.85em
}
  
/***
 *** Modules:Forum
 ***/
.forumpost,
.forumheaderlist  {
  font-size:0.8em
}
.forumnodiscuss {
  font-weight:bold
}
.forumpost .topic .subject {
  font-weight:bold
}
.forumpost .topic .author {
  font-size:0.8em
}
.forumpost .commands,
.forumpost .link {
  font-size:0.9em
}
.forumheaderlist .discussion .lastpost {
  font-size:0.7em
}
body#mod-forum-search .introcontent {
  font-weight:bold
}
body#mod-forum-index .cell {
  font-size:0.85em
}

/***
 *** Modules:Glossary
 ***/

.glossarydisplay,
.glossarysearchbox {
  font-size:0.8em
}
.glossarypost .commands {
  font-size:0.8em
}
.glossarypost .entryheader .author,
.glossarypost .entryheader .time {
  font-size:0.8em
}
.glossarypost .entryheader .time {
  font-style:italic
}
.glossarypopup {
  font-size:0.8em
}
.concept {
  font-weight:bold
}
.glossarycomment .time {
  font-size:0.8em;
  font-style:italic
}
.entrylowersection .aliases {
  font-size:0.8em
}
.entrylowersection .icons,
.entrylowersection .ratings {
  font-size:0.8em
}
#mod-glossary-index .cell {
  font-size:0.85em
}

/***
 *** Modules:Journal
 ***/
#mod-journal-view .lastedit,
#mod-journal-view .editend {
  font-size:0.7em
}
#mod-journal-view .author {
  font-size:1em;
  font-weight:bold
}
#mod-journal-view .time {
  font-size:0.7em;
  font-style:italic
}
#mod-journal-view .grade {
  font-weight:bold;
  font-style:italic
}
#mod-journal-index .cell {
  font-size:0.85em
}
/***
 *** Modules:Label
 ***/

/***
 *** Modules:Lesson
 ***/

#mod-lesson-index .cell {
  font-size:0.85em
}
/***
 *** Modules:Quiz
 ***/

.editorhelptext {
  font-size:0.7em
}
#mod-quiz-index .cell {
  font-size:0.85em
}

/***
 *** Modules:Resource
 ***/

#mod-resource-index .cell {
  font-size:0.85em
}
/*.mod-resource font {
  font-size:0.8em
}*/

/***
 *** Modules:Scorm
 ***/

#mod-scorm-index .cell {
  font-size:0.85em
}
/***
 *** Modules:Survey
 ***/

#mod-survey-index .cell {
  font-size:0.85em
}
/***
 *** Modules:Wiki
 ***/

#mod-wiki-index .cell {
  font-size:0.85em
}
/***
 *** Modules:Workshop
 ***/

#mod-workshop-index .cell {
  font-size:0.85em
}
/***** orangewhite/styles_fonts.css end *****/

/***** orangewhite/styles_color.css start *****/

/*******************************************************************
 styles_color.css
  
 This CSS file contains all color definitions like 
 background-color, font-color, border-color etc.

 Styles are organised into the following sections:

  core
  header
  footer

  admin
  blocks
  calendar
  course
  doc
  login
  message
  user

  various modules

*******************************************************************/

/***
 ***  Core
 ***/

a:link {
  color:#0F3C8C
}
a:visited {
  color:#AE1107
}
a.dimmed:link,
a.dimmed:visited {
  color:#AAA
}
a:hover {
  color:#F00
}
a.autolink {
  background-color:#FFEDCE;
  border-bottom:1px solid #A74;
}
body {
  background-color:#FFF
}
h1.main,
h2.main,
h3.main,
h4.main,
h5.main,
h6.main {
  color:#999
}
h1.main:first-letter,
h2.main:first-letter,
h3.main:first-letter,
h4.main:first-letter,
h5.main:first-letter,
h6.main:first-letter {
  color:#FFB63B
}
#layout-table #left-column {
  /*background-color:#F9FAFA*/
}
table.formtable tbody th {
  background:none
}
.highlight {
  background-color:#AFA
}
.highlight2 {
  color:#A00
}
/* Alternate rows even */
.r0 {  
}
/* Alternate rows odd */
.r1 {  
}
/* notification messages (can be good or bad) */
.breadcrumb .sep {
  color: #DA3; 
}
.notifyproblem {
  color:#600
}
.notifysuccess {
  color:#060
}
.required {
  background-color:#FFEDCE
}
.generalbox {
  border-color:#DDD;
  background-color:#F9FAFA
}
.informationbox {
  border-color:#DDD
}
.feedbackbox {
  border-color:#888
}
.feedbackby {
  background-color:#BBB
}
.noticebox {
  border-color:#DDD
}
.errorbox {
  color:#FFF;
  border-color:#600;
  background-color:#900
}
.tabledivider {
  border-color:#DDD
}
.sitetopic {
}
.sitetopiccontent {
  border-color:#DDD;
  background-color:#FFF
}
.dimmed_text {
  color:#AAA
}
.teacheronly {
  color:#900
}
.unread {
  background:#FFD991
}
.censoredtext {
  color:#000;
  background:#000
}
.generaltable th.header {
  background-color:#FFDA9D;
  border-color:#FFB63B
}
.generaltable .r1 {
  background-color:#D7D7D7
}
.generaltable {
  border-color: #bbbbbb;
}

/* kept for backward compatibility with some non-standard modules
   which use these classes for various things */
.generaltab, .generaltabinactive {
  background-color:#BBB
}
.generaltabselected {
  background-color:#FFEDCE
}
.generaltabinactive {
  color:#CCC
}

/***
 *** Header
 ***/

/***
 *** Footer
 ***/

#footer {
  background-color:#F9FAFA;
  border-top-color:#FFB63B
}
.homelink a:link,
.homelink a:visited,
.homelink a:hover {
  background-color:transparent;
  color:#000;
  text-decoration:none
}
.homelink a:link,
.homelink a:visited {
  border-top:1px solid #CECECE;
  border-bottom:2px solid #4A4A4A;
  border-left:1px solid #CECECE;
  border-right:2px solid #4A4A4A
}
.homelink a:hover {
  border-bottom:1px solid #CECECE;
  border-top:2px solid #4A4A4A;
  border-right:1px solid #CECECE;
  border-left:2px solid #4A4A4A
}

/***
 *** Admin
 ***/
 
.admin .generalboxcontent {
  background-color:#EEE
}
.admin .generalbox {
  border-color:#BBB
}
.admin .informationbox {
  border-color:#BBB; 
  background-color:#FFF
}
body#admin-index .c0 {
  background-color:#FAFAFA
}
#admin-config .r0 {
  background-color:#EEE
}
#admin-config .r0 .c0{
  background-color:#FFEDCE
}
#admin-config .r1 {
  color:#666
}
#admin-config h2.main {
  background-color:#FFEDCE
}
.logtable th.header {
  background-color:#FFDA9D;
  border-color:#FFB63B
}
#adminsettings fieldset span.form-shortname {
  color: #aaa;
}
#adminsettings fieldset {
  background-color: #f9fafa;
  border-color: #DDD;
}

/***
 *** Blocks
 ***/

.sideblock {
  border-color:#DDD;
  background-color:#F9FAFA
}
.sideblock .header {
  color:#666
}
.sideblock .header .commands {
  background-color:#F9FAFA;
}
.sideblock .content {
}
.sideblock .content hr {
  border-top-color:#999
}
.sideblock .list {
}
#left-column .sideblock .header {
  background-color:#DAE0E4;
  border-bottom-color:#F9FAFA
}
#left-column .sideblock .list td {
  border-color:#FFF
}
#right-column .sideblock .header {
  background-color:#FFEDCE;
  border-bottom-color:#CCDDEF
}
#right-column .sideblock .content {
  background-color:#F9FAFA
}
.sideblock .link {
  border-bottom-color:#FFF
}
.sideblock .post {
  border-bottom-color:#FFF
}
.sideblock h1.main:first-letter,
.sideblock h2.main:first-letter,
.sideblock h3.main:first-letter,
.sideblock h4.main:first-letter,
.sideblock h5.main:first-letter,
.sideblock h6.main:first-letter {
  color:#999
}


/***
 *** Calendar
 ***/

#calendar .maincalendar,
#calendar .sidecalendar {
  border-color:#DDD
}
#calendar .maincalendar table.calendarmonth th {
  border-color:#000
}
table.minicalendar {
  border-color:#DDD
}
#calendar .maincalendar .eventlist .event {
  border-color:#DDD
}
#calendar .maincalendar .eventlist .event .topic,
#calendar .maincalendar .eventlist .event .picture,
#calendar .maincalendar .eventlist .event .side {
  background-color:#EEE
}
#calendar .maincalendar table.calendarmonth ul.events-underway {
  color:#999
}
#calendar .event_global,
.minicalendar .event_global,
.block_calendar_month .event_global {
  border-color:#2EBA0E !important;
  background-color:#2EBA0E
}
#calendar .event_course,
.minicalendar .event_course,
.block_calendar_month .event_course {
  border-color:#F96 !important;
  background-color:#F96
}
#calendar .event_group,
.minicalendar .event_group,
.block_calendar_month .event_group {
  border-color:#FBBB23 !important;
  background-color:#FBBB23
}
#calendar .event_user,
.minicalendar .event_user,
.block_calendar_month .event_user {
  border-color:#A1BECB !important;
  background-color:#A1BECB
}
#calendar .duration_global,
.minicalendar .duration_global {
  border-top-color:#2EBA0E !important;
  border-bottom-color:#2EBA0E !important
}
#calendar .duration_course,
.minicalendar .duration_course {
  border-top-color:#F96 !important;
  border-bottom-color:#F96 !important
}
#calendar .duration_group,
.minicalendar .duration_group {
  border-top-color:#FBBB23 !important;
  border-bottom-color:#FBBB23 !important
}
#calendar .duration_user,
.minicalendar .duration_user {
  border-top-color:#A1BECB !important;
  border-bottom-color:#A1BECB !important
}
#calendar .weekend,
.minicalendar .weekend {
  color:#F00
}
#calendar .today,
.minicalendar .today {
  border-color:#DDD !important
}
.cal_popup_fg {
  background-color:#FFF
}
.cal_popup_bg {
  border-color:#000;
  background-color:#FFF
}
#calendar .maincalendar .filters table,
#calendar .sidecalendar .filters table,
.block_calendar_month .filters table {
  background-color:transparent
}


/***
 *** Course
 ***/

#course-view .headingblock {
  border-left-color:#FFC86C
}
#course-view .section td {
  border-color:#DDD
}
#course-view .section .content .section {
  background-color:#F9FAFA
}
#course-view .section .content .section .activity {
  border-bottom-color:#FFF
}
#course-view .section .content .section .label {
  background-color:#FFF
}
#course-view .section .side {
}
#course-view .section .left {
  background-color:#FFC86C
}
#course-view .section .right {
}
#course-view .current .side{
}
#course-view .topics {
}
#course-view .weeks {
}
#course-view .section .spacer {
}
#course-view .section .weekdates {
  color:#AAA
}
/*.editing .section .content .summary {
  background-color:#FFF
}*/
.categoryboxcontent,
.courseboxcontent {
  border-color:#DDD;
  background:#FFF
}
#course-user .section {
  border-color:#AAA
}
#course-user #content,
#user-view #content,
#user-edit #content,
.admin #content,
.course #content,
#files-index #content {
  background-color:#EEE
}
#user-edit #content .generalbox,
#user-view .userinfobox tr {
  background-color:#FFF
}
#site-index #content,
#course-view #content {
  background-color:#FFF
}
#course-user .logtable .r1 {
  background-color:#DFDFDF
}
#course-view .section.hidden *,
#course-view .section.hidden .content,
#course-view .section.hidden .side {
  border-color:#EEE;
  color:#AAA
}
#course-view .section.hidden .left {
  background-color:#EEE
}

/***
 *** Doc
 ***/

/***
 *** Login
 ***/

.loginbox {
  background-color:#F9FAFA
}

.loginbox,
.loginbox.twocolumns .loginpanel,
.loginbox .subcontent {
  border-color:#DDD
}

/***
 *** Message
 ***/

table.message_search_results td {
  border-color:#DDD
}
.message.other .author {
  color:#88C
}
.message.me .author {
  color:#999
}
.message .time {
  color:#999
}
.message .content {
}

/***
 *** User
 ***/

.userpicture {
  background:#EEE
}
.userinfobox {
  border-color:#DDD;
  background-color:#F7F7F7
}
.groupinfobox {
  border-color:#DDD;
  background-color:#FCFCFC
}

/***
 *** Modules:Assignment
 ***/

.assignmentsubmission {
}
.assignmentnew .assignmentfeedback{
  background-color:#FFEDCE
}   
  
.assignmentold .assignmentfeedback{
  background-color:#BBB
}
.assignmentheading {
  background-color:#BBB
}

/***
 *** Modules:Chat
 ***/

/***
 *** Modules:Choice
 ***/

/***
 *** Modules:Forum
 ***/
 
.mod-forum #content {
  background-color:#F9FAFA
}
#mod-forum-user #content {
  background-color:#EEE
}
.forumheaderlist,
.forumpost {
  border-color:#DDD;
  background-color:#FFF
}
.forumheaderlist .header {
  background-color:#FFDA9D;
  border-color:#FFB63B
}
.forumpost .content {
  background:#FFF
}
.forumpost .left {
  background:#EEE
}
.forumpost .topic {
  border-bottom-color:#EEE
}
.forumpost .starter {
  background-color:#FFDA9D
}
.forumheaderlist .discussion .starter {
  background-color:#FFEDCE
}
.forumheaderlist td {
  border-color:#FFF
}
.sideblock .post .head {
  color:#555
}
.forumthread .unread {
  background:#FFD991
}
#mod-forum-discuss .forumpost {
  background:none
}
#mod-forum-discuss .forumpost.unread .content {
/*  border-color:#FFD991 */
}
#mod-forum-discuss .forumthread .unread {
}
#mod-forum-index .unread {
}

/***
 *** Modules:Glossary
 ***/
 
.mod-glossary #content {
 background-color:#F9FAFA
}
.entryboxheader {
  border-color:#BBB
}
.entrybox {
  border-color:#BBB;
  background-color:#FFF
}
.glossarypost {
  border-color:#DDD;
  background-color:#FFF
}
.glossarypost .entryheader,
.glossarypost .entryapproval,
.glossarypost .picture,
.glossarypost .entryattachment,
.glossarypost .left {
  background-color:#F0F0F0
}
.glossarycomment {
  border-color:#DDD
}
.glossarycomment .entryheader,
.glossarycomment .picture,
.glossarycomment .left {
  background-color:#F0F0F0
}
#mod-glossary-report .generalbox .teacher {
  background:#F0F0F0
}
.glossarycategoryheader {
  background-color:#FFDA9D
}
.glossaryformatheader {
  background-color:#FFEDCE
}
.glossarydisplay {
  border-bottom-color:#F9FAFA
}

/***
 *** Modules:Journal
 ***/

#mod-journal-view .feedbackbox .left,
#mod-journal-view .feedbackbox .entryheader {
  background-color:#FFEDCE
}
/***
 *** Modules:Label
 ***/

/***
 *** Modules:Lesson
 ***/

/***
 *** Modules:Quiz
 ***/

body#mod-quiz-report table#attempts td {
  border-color:#DDD
}
body#mod-quiz-report table#attempts .r1 {
  background-color:#EEE
}

/***
 *** Modules:Resource
 ***/

/***
 *** Modules:Scorm
 ***/

/***
 *** Modules:Survey
 ***/

#mod-survey-view .r0 {
  background-color:#EEE
}
#mod-survey-view .r1 {
  background-color:#FFEDCE
}

/***
 *** Modules:Wiki
 ***/

/***
 *** Modules:Workshop
 ***/

.workshoppostpicture {
  background-color:#FEE6B9
}
.workshopassessmentheading {
  background-color:#FFEDCE
}
/***** orangewhite/styles_color.css end *****/

/***** orangewhite/styles_tabs.css start *****/

/*******************************************************************
*** Tabs
*******************************************************************/

.userinfobox {
  border-top:0 none;
  padding-top:0;
  margin-top:0;
}

#mod-forum-user .forumpost,
#course-user .section .content {
  border-top:0 none;
}

#course-user .section {
  background-color:#fff;
  padding:1em;
}

#course-user .section h2 {
  margin-top:0;
}

#user-view .tabs td,
#user-edit .tabs td,
#mod-forum-user .tabs td {
  padding-bottom:0;
}

#user-edit .generalbox {
  width:100%
}

.mod-glossary .glossarydisplay tr,
.mod-glossary .glossarydisplay td {
  border:0 none !important;
  padding-bottom:0;
}

.mod-glossary td.entryboxheader {
  height:0 !important;
  background-color:#fff;
}

.mod-glossary .entrybox {
  padding:0;
}

.tabs {
  width:auto;
  margin-left:auto;
  margin-right:auto;
  margin-bottom:0;
  padding-bottom:0;
  border-bottom:0 none;
}

#user-view .tabs {
  width:80%;
}

.tabs tr,
.tabs .left,
.tabs .right {
  background:url(pix/tab/tabsbg_x2.gif) bottom left repeat-x
}

.tabs .side {
  border-bottom:0 none
}

.tabs td {
  padding:0
}

.tabs .left {
  width:0
}

.tabs .right {
  width:75%
}

.tabrow {
  width:100%;
  margin:0;
  border-collapse:collapse
}

.tabrow td {
  padding:0 0 0 14px;
  height:34px;
  border-width:0
}

.tabs .r1 {
  margin-bottom:1px
}
.tabrow td.selected {
  border-width: 0px
}

.tabs .r0 .active {
  background:url(pix/tab/left.gif) bottom left no-repeat
}

.tabs .r1 .active {
  background:url(pix/tab/left2.gif) bottom left no-repeat
}

.tabs .r0 .inactive {
  background:url(pix/tab/left_inactive.gif) bottom left no-repeat
}

.tabs .r1 .inactive {
  background:url(pix/tab/left_inactive2.gif) bottom left no-repeat
}

.tabs .r0 .activetwo {
  background:url(pix/tab/left_active2.gif) bottom left no-repeat
}

.tabs .r1 .activetwo {
  background:url(pix/tab/left_active2.gif) bottom left no-repeat
}

.tabs,
.tabs tr,
.tabs .td,
.tabrow,
.tabrow tbody,
.tabrow tr,
.tabrow td {
  background-color:transparent
}

.tabrow th {
  display:none
}

.tabrow td .tablink {
  padding:0 14px 0 0;
  /*display:block;*/
  white-space:nowrap;
  line-height:32px;
  text-align:center;
  text-decoration:none;
  height:34px;
  width:auto
}

.tabs .r0 .active .tablink {
  background:url(pix/tab/right.gif) bottom right no-repeat
}

.tabs .r1 .active .tablink {
  background:url(pix/tab/right2.gif) bottom right no-repeat
}

.tabs .r0 .inactive .tablink {
  background:url(pix/tab/right_inactive.gif) bottom right no-repeat
}

.tabs .r1 .inactive .tablink {
  background:url(pix/tab/right_inactive2.gif) bottom right no-repeat
}

.tabs .r0 .activetwo .tablink {
  background:url(pix/tab/right_active2.gif) bottom right no-repeat
}

.tabs .r1 .activetwo .tablink {
  background:url(pix/tab/right_active2.gif) bottom right no-repeat
}

.tabrow td .tablink a {
  width:auto;
  line-height:32px
}

.tabs .r0 .active:hover {
  background:url(pix/tab/left_hover.gif) bottom left no-repeat
}

.tabs .r0 .active:hover .tablink {
  background:url(pix/tab/right_hover.gif) bottom right no-repeat;
  line-height:32px
}

.tabs .r0 .inactive:hover {
  background:url(pix/tab/left_inactive.gif) bottom left no-repeat
}

.tabs .r0 .inactive:hover .tablink {
  background:url(pix/tab/right_inactive.gif) bottom right no-repeat;
  line-height:32px
}

.tabs .r1 .active:hover {
  background:url(pix/tab/left_hover2.gif) bottom left no-repeat
}

.tabs .r1 .active:hover .tablink {
  background:url(pix/tab/right_hover2.gif) bottom right no-repeat;
  line-height:32px
}

.tabrow .last span {
  padding:0 1px 0 0;
  display:block;
  background:url(pix/tab/right_end.gif) bottom right no-repeat
}

.tabs .r0 .selected {
  background:url(pix/tab/left_active.gif) bottom left no-repeat
}

.tabs .r1 .selected {
  background:url(pix/tab/left_active2.gif) bottom left no-repeat
}

.tabs .r0 .selected .tablink {
  background:url(pix/tab/right_active.gif) bottom right no-repeat;
  line-height:32px
}

.tabs .r1 .selected .tablink {
  background:url(pix/tab/right_active2.gif) bottom right no-repeat;
  line-height:32px
}

/*.tabrow td.selected:hover  {
  background:url(pix/tab/left_active.gif) bottom left no-repeat;
}

.tabrow td.selected .tablink:hover {
  background:url(pix/tab/right_active.gif) bottom right no-repeat;
}*/

.user-content h2 {
  margin:0;
  padding:0 1em
}

.user-content {
  background-color:#FFFFFF;
  border:1px solid #D1D7DC;
  border-top-width:0;
  padding:0.5em
}


/*******************************************************************
*** Tabs
*******************************************************************/
.tabs {
  font-size:0.8em
}
.tablink a:link,
.tablink a:visited {
    color:#000066;
}

.tablink a:hover {
    text-decoration: none;
}

.selected .tablink a:link,
.selected .tablink a:visited {
    color:#000000;
}/***** orangewhite/styles_tabs.css end *****/

