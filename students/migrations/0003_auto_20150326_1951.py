# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
        ('students', '0002_progress'),
    ]

    operations = [
        migrations.CreateModel(
            name='Logins',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('login_date', models.DateTimeField()),
                ('student', models.ForeignKey(to='students.Student')),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.AlterField(
            model_name='progress',
            name='AcademicMajor_completed',
            field=models.BooleanField(),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='progress',
            name='Address_completed',
            field=models.BooleanField(),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='progress',
            name='Application_submitted',
            field=models.BooleanField(),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='progress',
            name='Citizenship_completed',
            field=models.BooleanField(),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='progress',
            name='HighSchoolTranscript_received',
            field=models.BooleanField(),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='progress',
            name='PersonalInformation_completed',
            field=models.BooleanField(),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='progress',
            name='PreviousEducation_completed',
            field=models.BooleanField(),
            preserve_default=True,
        ),
    ]
