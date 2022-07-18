# apka dot. wodociągów 

1. Set laravel
composer install
copy .env.example to env
php artisan key:generate

2. Set access rights
sudo chgrp -R www-data storage bootstrap/cache
sudo chmod -R ug+rwx storage bootstrap/cache
cut -d: -f1 /etc/group
sudo adduser daemon www-data

3. Fix full-text search:
php artisan index:table tableName fild1,field2
fix bug on line 83 of Search.php "\\App\\" should be name of your App folder!

4. Enjoy!