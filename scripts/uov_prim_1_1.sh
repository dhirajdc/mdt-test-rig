fname=`basename $0`
var=`grep $fname params.txt | cut -d, -f2`
curl -XPOST http://127.0.0.1:8090/api/notebook/job/${var}
