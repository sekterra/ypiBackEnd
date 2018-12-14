git pull origin master
git add *
set str=

set /p str=커밋 메시지를 입력하세요:

if "%str%" == "" %str% = "Push to git at vuetify_upgrade branch for IOS test"

git commit -m "%str%"
git push origin master
echo ======= GIT push complete ======