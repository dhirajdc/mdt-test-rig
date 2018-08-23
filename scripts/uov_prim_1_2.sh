fname=`basename $0`
var=`grep $fname /home/mdt-worker/poc/work/params.txt | cut -d, -f2`
curl -XPOST http://127.0.0.1:8080/api/notebook/job/${var}
