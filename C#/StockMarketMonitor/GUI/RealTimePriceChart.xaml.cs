﻿using LiveCharts;
using LiveCharts.Configurations;
using StockMonitor.Helpers;
using StockMonitor.Models.ApiModels;
using StockMonitor.Models.UIClasses;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Shapes;

namespace GUI
{
    /// <summary>
    /// Interaction logic for RealTimePriceChart.xaml
    /// </summary>
    public partial class RealTimePriceChart : Window, INotifyPropertyChanged
    {
        public string Symbol { get; set; }
        public ChartValues<FmgQuoteOnlyPriceWrapper> ChartValues { get; set; }
        public Func<double, string> DateTimeFormatter { get; set; }
        public double AxisStep { get; set; }
        public double AxisUnit { get; set; }

        double _axisMax;
        double _axisMin;

        public double AxisMax
        {
            get { return _axisMax; }
            set
            {
                _axisMax = value;
                OnPropertyChanged("AxisMax");
            }
        }
        public double AxisMin
        {
            get { return _axisMin; }
            set
            {
                _axisMin = value;
                OnPropertyChanged("AxisMin");
            }
        }
        public RealTimePriceChart(UIComapnyRow selCompany,CancellationToken ct)
        {
            InitializeComponent();

            Symbol = selCompany.Symbol;

            txtSymbol.Text = Symbol;

            var mappers = Mappers.Xy<FmgQuoteOnlyPriceWrapper>()
                .X(model => model.Time.Ticks)
                .Y(model => model.Price);

            Charting.For<FmgQuoteOnlyPriceWrapper>(mappers);

            ChartValues = new ChartValues<FmgQuoteOnlyPriceWrapper>();

            DateTimeFormatter = value => new DateTime((long) value).ToString("mm:ss");

            AxisStep = TimeSpan.FromSeconds(3).Ticks;

            AxisUnit = TimeSpan.TicksPerSecond;

            SetAxisLimits(DateTime.Now);

            DataContext = this;

            Read(ct);
        }

        async void Read(CancellationToken ct)
        {
            while (true)
            {
                ct.ThrowIfCancellationRequested();
                await Task.Delay(500);

                var realTimePrice = (from companyRow in GlobalVariables.DefaultUICompanyRows
                                     where companyRow.Symbol == Symbol
                                     select companyRow.Price).First<double>();

                DateTime now = DateTime.Now;
                ChartValues.Add(new FmgQuoteOnlyPriceWrapper() { Price = realTimePrice, Time = now });

                SetAxisLimits(now);
                txtPrice.Text = "$" + realTimePrice.ToString("N2");

                if (ChartValues.Count > 20) { ChartValues.RemoveAt(0); }
            }
        }

        void SetAxisLimits(DateTime now)
        {
            AxisMax = now.Ticks + TimeSpan.FromSeconds(1).Ticks;
            AxisMin = now.Ticks - TimeSpan.FromSeconds(8).Ticks;
        }


        #region INotifyPropertyChanged implementation

        public event PropertyChangedEventHandler PropertyChanged;

        protected virtual void OnPropertyChanged(string propertyName = null)
        {
            if (PropertyChanged != null)
                PropertyChanged.Invoke(this, new PropertyChangedEventArgs(propertyName));
        }

        #endregion

    }
    public class FmgQuoteOnlyPriceWrapper
    {
        public double Price { get; set; }
        public DateTime Time { get; set; }
    }
}
